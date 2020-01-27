package com.sonnebtb.wowguildmanager

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
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

