package com.sonnebtb.wowguildmanager.guildinteraction

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.MainActivity
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.guildinteraction.announcements.AnnouncementClickListener
import com.sonnebtb.wowguildmanager.guildinteraction.announcements.AnnouncementsFragment
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalendarEvent
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalendarFragment
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalenderEventClickListener
import com.sonnebtb.wowguildmanager.guildinteraction.members.MembersFragment
import com.sonnebtb.wowguildmanager.guildinteraction.polls.PollsEventClickListener
import com.sonnebtb.wowguildmanager.guildinteraction.polls.PollsFragment
import kotlinx.android.synthetic.main.activity_guild_interaction.*
import kotlinx.android.synthetic.main.card_poll.view.*
import kotlinx.android.synthetic.main.dialog_new_event.*
import kotlinx.android.synthetic.main.dialog_new_event.view.*
import java.util.*

class GuildInteractionActivity : AppCompatActivity(), PollsEventClickListener, CalenderEventClickListener, AnnouncementClickListener {
    private val calendarRef = FirebaseFirestore
        .getInstance()
        .collection("CalendarEvents")

    private val announcementRef = FirebaseFirestore
        .getInstance()
        .collection("Announcements")

    private val pollsRef = FirebaseFirestore
        .getInstance()
        .collection("Polls")

    override fun calendarFabClicked() {
        showAddEventDialog()
    }

    override fun announcementFabClicked() {
        showAddAnnouncementDialog()
    }

    override fun pollFabClicked() {
        showAddPollDialog()
    }

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        signInAnonymously()

        setContentView(R.layout.activity_guild_interaction)
        nav_view.setOnNavigationItemSelectedListener {
            var switchTo: Fragment? = null
            val handled = when(it.itemId) {
                R.id.navigation_announcements -> {
                    switchTo = AnnouncementsFragment(this)
                    true
                }
                R.id.navigation_members -> {
                    switchTo = MembersFragment()
                    true
                }
                R.id.navigation_calendar -> {
                    switchTo = CalendarFragment(this, calendarRef)
                    true
                }
                R.id.navigation_polls -> {
                    switchTo = PollsFragment(this)
                    true
                }
                else -> false
            }
            launchFragment(switchTo)
            handled
        }
        launchFragment(MembersFragment())
    }

    private fun signInAnonymously() {
        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    Toast.makeText(this, "Signed in successfully", Toast.LENGTH_SHORT)
                    Log.d(Constants.TAG, "Signed in successfully")
                } else {
                    Toast.makeText(this, "Auth Failed bro", Toast.LENGTH_SHORT)
                    Log.d(Constants.TAG, "Auth Failed bro")
                }

                // ...
            }
    }

    fun showAddPollDialog() {
        val builder = AlertDialog.Builder(this, R.style.AlertDialogCustom)
        //Set options
        builder.setTitle("New Guild Poll")
        // Content is message, view, or a list of items.
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_new_guild_poll, null, false)
        builder.setView(view)
        builder.setPositiveButton(android.R.string.ok){_, _ ->

        }
        builder.setNegativeButton(android.R.string.cancel, null)
        builder.create().show()
    }


    var dateSetListener: DatePickerDialog.OnDateSetListener? = null
    fun showAddEventDialog() {
        val builder = AlertDialog.Builder(this, R.style.AlertDialogCustom)
        //Set options
        builder.setTitle("New Event")

        val event = CalendarEvent()
        // Content is message, view, or a list of items.
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_new_event, null, false)

        dateSetListener = DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
            val date = "$month/$dayOfMonth/$year"

            dialogView.new_event_date.text = date
            Log.d(Constants.TAG, date)
        }

        dialogView.new_event_date.setOnClickListener {
            val calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)


            var dialog: DatePickerDialog = DatePickerDialog(
                this,
                android.R.style.ThemeOverlay_Material_Dark,
                dateSetListener,
                year, month, day
            )

            dialog.show()

        }

        builder.setView(dialogView)
        builder.setPositiveButton(android.R.string.ok){_, _ ->

            calendarRef.add(
                CalendarEvent(
                    createDate=getToday(),
                    endDate = dialogView.new_event_date.toString(),
                    title = dialogView.new_event_title_edit_text.text.toString(),
                    desc = dialogView.new_event_description_edit_text.text.toString())
            )
        }
        builder.setNegativeButton(android.R.string.cancel, null)
        builder.create().show()
    }

    fun getToday(): String {
        val calendar = Calendar.getInstance()
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)
        val date = "$month/$day/$year"
        return date
    }

    fun showAddAnnouncementDialog() {
        val builder = AlertDialog.Builder(this, R.style.AlertDialogCustom)
        //Set options
        builder.setTitle("New Announcement")

        // Content is message, view, or a list of items.
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_new_announcement, null, false)
        builder.setView(view)
        builder.setPositiveButton(android.R.string.ok){_, _ ->

        }
        builder.setNegativeButton(android.R.string.cancel, null)
        builder.create().show()
    }

    fun launchFragment(switchTo: Fragment?) {
        if (switchTo != null ){
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_container, switchTo)
            ft.commit()
        }
    }
}

