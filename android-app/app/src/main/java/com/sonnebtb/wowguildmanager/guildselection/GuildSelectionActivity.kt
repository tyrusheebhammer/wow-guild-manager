package com.sonnebtb.wowguildmanager.guildselection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sonnebtb.wowguildmanager.R
import kotlinx.android.synthetic.main.activity_guild_selection.*

class GuildSelectionActivity : AppCompatActivity() {

    lateinit var adapter: GuildAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guild_selection)
        adapter = GuildAdapter(this)
        guild_selection_recycler_view.layoutManager = LinearLayoutManager(this)
        //recycler_view.layoutManager = GridLayoutManager(this, 2)
        guild_selection_recycler_view.setHasFixedSize(true)
        guild_selection_recycler_view.adapter = adapter
    }
}
