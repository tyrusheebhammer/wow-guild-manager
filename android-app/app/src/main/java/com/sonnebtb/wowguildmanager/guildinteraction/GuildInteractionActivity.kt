package com.sonnebtb.wowguildmanager.guildinteraction

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.guildinteraction.announcements.AnnouncementClickListener
import com.sonnebtb.wowguildmanager.guildinteraction.announcements.AnnouncementsFragment
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalendarFragment
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalenderEventClickListener
import com.sonnebtb.wowguildmanager.guildinteraction.members.MembersFragment
import com.sonnebtb.wowguildmanager.guildinteraction.polls.PollsEventClickListener
import com.sonnebtb.wowguildmanager.guildinteraction.polls.PollsFragment
import kotlinx.android.synthetic.main.activity_guild_interaction.*

class GuildInteractionActivity : AppCompatActivity(), PollsEventClickListener, CalenderEventClickListener, AnnouncementClickListener {
    override fun calendarFabClicked() {
        showAddEventDialog()
    }

    override fun announcementFabClicked() {
        showAddAnnouncementDialog()
    }

    override fun pollFabClicked() {
        showAddPollDialog()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    switchTo = CalendarFragment(this)
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

    fun showAddEventDialog() {
        val builder = AlertDialog.Builder(this, R.style.AlertDialogCustom)
        //Set options
        builder.setTitle("New Event")

        // Content is message, view, or a list of items.
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_new_event, null, false)
        builder.setView(view)
        builder.setPositiveButton(android.R.string.ok){_, _ ->

        }
        builder.setNegativeButton(android.R.string.cancel, null)
        builder.create().show()
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

