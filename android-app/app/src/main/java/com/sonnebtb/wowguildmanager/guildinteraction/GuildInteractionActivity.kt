package com.sonnebtb.wowguildmanager.guildinteraction

import android.app.DatePickerDialog
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.guildinteraction.announcements.Announcement
import com.sonnebtb.wowguildmanager.guildinteraction.announcements.AnnouncementClickListener
import com.sonnebtb.wowguildmanager.guildinteraction.announcements.AnnouncementsFragment
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalendarEvent
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalendarFragment
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalenderEventClickListener
import com.sonnebtb.wowguildmanager.guildinteraction.members.MembersFragment
import com.sonnebtb.wowguildmanager.guildinteraction.polls.Poll
import com.sonnebtb.wowguildmanager.guildinteraction.polls.PollsEventClickListener
import com.sonnebtb.wowguildmanager.guildinteraction.polls.PollsFragment
import com.sonnebtb.wowguildmanager.responses.BlizzardEndpointHelper
import com.sonnebtb.wowguildmanager.responses.Guild
import com.sonnebtb.wowguildmanager.responses.MemberCharacter
import com.sonnebtb.wowguildmanager.responses.UserInfo
import kotlinx.android.synthetic.main.activity_guild_interaction.*
import kotlinx.android.synthetic.main.dialog_new_announcement.view.*
import kotlinx.android.synthetic.main.dialog_new_event.view.*
import kotlinx.android.synthetic.main.dialog_new_guild_poll.view.*
import java.util.*
import kotlin.collections.ArrayList

class GuildInteractionActivity : AppCompatActivity(), PollsEventClickListener, CalenderEventClickListener, AnnouncementClickListener, FirebaseDeleteDelegate {
    private val guildID = "mlJ1AkLOg4id5jPFHKmb"
    private val mainRef = FirebaseFirestore
        .getInstance()
//        .collection(Constants.MAIN_COLLECTION_KEY)


    private val calendarRef = mainRef.collection("CalendarEvents")

    private val announcementRef = mainRef.collection("Announcements")

    private val pollsRef = mainRef.collection("Polls")

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
    private var guild: Guild? = null
    private var guildMembers: MutableList<MemberCharacter>? = null
    lateinit var userInfo: UserInfo
    var battletag: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val allPref = preferences.all
        val token = allPref[Constants.ACCESS_TOKEN]!! as String
        BlizzardEndpointHelper.setToken(token)

        BlizzardEndpointHelper.getUserInfo {
            Log.e(Constants.TAG, "$it")
            userInfo = it
        }

        guild = intent?.getParcelableExtra(Guild.GUILD_PARCEL)

        Log.d(Constants.TAG, "battle tag $battletag")

        guild?.let {
            BlizzardEndpointHelper.generateRosterForSelectedGuild(it) {result ->
                Log.d(Constants.TAG, "Completed roster generation")
                guildMembers = result
                runOnUiThread {
                    Toast.makeText(this, "Members Ready", Toast.LENGTH_LONG)
                }

            }
        }
        auth = FirebaseAuth.getInstance()
        signInAnonymously()
        setContentView(R.layout.activity_guild_interaction)
        setNavBarListeners()
        launchFragment(MembersFragment(guildMembers?: ArrayList()))
    }

    private fun setNavBarListeners() {
        nav_view.setOnNavigationItemSelectedListener {
            var switchTo: Fragment? = null
            val handled = when(it.itemId) {
                R.id.navigation_announcements -> {
                    switchTo = AnnouncementsFragment(this, announcementRef, guildID, guild=guild!!, deleteDelegate=this)
                    true
                }
                R.id.navigation_members -> {
                    Log.d(Constants.TAG, "$guildMembers")
                    switchTo = MembersFragment(guildMembers?: ArrayList())
                    true
                }
                R.id.navigation_calendar -> {
                    switchTo = CalendarFragment(this, calendarRef, guild=guild!!, deleteDelegate=this)
                    true
                }
                R.id.navigation_polls -> {
                    switchTo = PollsFragment(this, pollsRef, guild=guild!!, deleteDelegate=this)
                    true
                }
                else -> false
            }
            launchFragment(switchTo)
            handled
        }
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
        connectDatePicker(view.new_poll_end_date)
        builder.setView(view)
        builder.setPositiveButton(android.R.string.ok){_, _ ->
            pollsRef.add(
                Poll(
                    createDate = getToday(),
                    desc = view.new_poll_description_edit_text.text.toString(),
                    title = view.new_poll_title_edit_text.text.toString(),
                    link = view.new_poll_url_edit_text.text.toString(),
                    validDate = view.new_poll_end_date.text.toString(),
                    guild = guild!!.compound,
                    creator = userInfo.battletag ?: ""
                )
            )
            if (view.create_announcement_checkbox.isChecked){
                announcementRef.add(
                    Announcement(
                        createDate = getToday(),
                        desc = view.new_poll_description_edit_text.text.toString(),
                        guild = guild!!.compound,
                        title = view.new_poll_title_edit_text.text.toString(),
                        creator = userInfo.battletag ?: ""
                    )
                )
            }
        }
        builder.setNegativeButton(android.R.string.cancel, null)
        builder.create().show()
    }


    var dateSetListener: DatePickerDialog.OnDateSetListener? = null

    fun connectDatePicker(dateTextView: TextView) {
        dateSetListener = DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
            val date = "${month+1}/$dayOfMonth/$year"

            dateTextView.text = date
            Log.d(Constants.TAG, date)
        }

        dateTextView.setOnClickListener {
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
    }



    fun showAddEventDialog() {
        val builder = AlertDialog.Builder(this, R.style.AlertDialogCustom)
        //Set options
        builder.setTitle("New Event")

        // Content is message, view, or a list of items.
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_new_event, null, false)

        connectDatePicker(view.new_event_date)

        builder.setView(view)
        builder.setPositiveButton(android.R.string.ok){_, _ ->
            calendarRef.add(
                CalendarEvent(
                    createDate=getToday(),
                    endDate = view.new_event_date.text.toString(),
                    title = view.new_event_title_edit_text.text.toString(),
                    desc = view.new_event_description_edit_text.text.toString(),
                    guild = guild!!.compound,
                    creator = userInfo.battletag ?: "")

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
        val date = "${month+1}/$day/$year"
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
            announcementRef.add(
                Announcement(
                    createDate = getToday(),
                    desc = view.announcement_description_edit_text.text.toString(),
                    guild = guild!!.compound,
                    title = view.announcement_title_edit_text.text.toString(),
                    creator = userInfo.battletag ?: ""
                )
            )
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

    override fun userIsCreator(contentCreator: String): Boolean {
        Log.d(Constants.TAG, "$contentCreator - ${userInfo.battletag}")
        return contentCreator == userInfo.battletag
    }
}

interface FirebaseDeleteDelegate {
    fun userIsCreator(contentCreator: String): Boolean
}