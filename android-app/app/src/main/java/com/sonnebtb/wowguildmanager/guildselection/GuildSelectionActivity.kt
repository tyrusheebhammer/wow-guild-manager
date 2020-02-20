package com.sonnebtb.wowguildmanager.guildselection

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.responses.BlizzardEndpointHelper
import com.sonnebtb.wowguildmanager.responses.UserInfo
import kotlinx.android.synthetic.main.activity_guild_selection.*


class GuildSelectionActivity : AppCompatActivity() {

    lateinit var adapter: GuildAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guild_selection)
        adapter = GuildAdapter(this)


        Log.d(Constants.TAG, "in the guild selection activity")

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val allPref = preferences.all
        val token = allPref[Constants.ACCESS_TOKEN]!! as String
        BlizzardEndpointHelper.setToken(token)


        BlizzardEndpointHelper.getUserCharacters {characters ->
            characters?.let {
                var guilds = BlizzardEndpointHelper.getUniqueGuilds(it)
                runOnUiThread {
                    adapter.guilds.addAll(0, guilds)
                    adapter.notifyDataSetChanged()

                }
            }
        }
        showView()
    }

    fun showView() {
        guild_selection_recycler_view.layoutManager = LinearLayoutManager(this)
        //recycler_view.layoutManager = GridLayoutManager(this, 2)
        guild_selection_recycler_view.setHasFixedSize(true)
        guild_selection_recycler_view.adapter = adapter
    }
}