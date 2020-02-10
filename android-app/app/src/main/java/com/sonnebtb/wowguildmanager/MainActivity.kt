package com.sonnebtb.wowguildmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sonnebtb.wowguildmanager.guildinteraction.GuildInteractionActivity
import com.sonnebtb.wowguildmanager.guildselection.GuildSelectionActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fab.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login_card.setOnClickListener {
            Log.d(Constants.TAG, "You want to Login")
            val guildSelectionIntent = Intent(this, GuildSelectionActivity::class.java)
            startActivity(guildSelectionIntent)
        }
    }


}
