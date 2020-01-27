package com.sonnebtb.wowguildmanager.guildinteraction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sonnebtb.wowguildmanager.R
import kotlinx.android.synthetic.main.activity_guild_interaction.*

class GuildInteractionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guild_interaction)
        nav_view.setOnNavigationItemSelectedListener {
            var switchTo: Fragment? = null
            val handled = when(it.itemId) {
                R.id.navigation_announcements -> {
                    true
                }
                R.id.navigation_members -> {
                    true
                }
                R.id.navigation_calendar -> {
                    true
                }
                R.id.navigation_polls -> {
                    true
                }
                else -> false
            }
            launchFragment(switchTo)
            handled
        }

    }

    fun launchFragment(switchTo: Fragment?) {
        if (switchTo != null ){
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_container, switchTo)
            ft.commit()
        }
    }
}

