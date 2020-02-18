package com.sonnebtb.wowguildmanager.guildselection

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dementh.lib.battlenet_oauth2.BnConstants.BUNDLE_BNPARAMS
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Helper
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params
import com.google.api.Http
import com.google.api.client.http.HttpRequest
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.R
import kotlinx.android.synthetic.main.activity_guild_selection.*


class GuildSelectionActivity : AppCompatActivity() {

    lateinit var adapter: GuildAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guild_selection)

        Log.d(Constants.TAG, "in the guild selection activity")

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val allPref = preferences.all
        val token = allPref[Constants.ACCESS_TOKEN]!! as String
        val userInfoEndPoint = "https://us.battle.net/oauth/userinfo?access_token=$token"
        val client = Http.newBuilder().build()
        val request = Http.
        adapter = GuildAdapter(this)
        adapter.createDemoGuilds() //TODO: remove to be compatible with actual data
        guild_selection_recycler_view.layoutManager = LinearLayoutManager(this)
        //recycler_view.layoutManager = GridLayoutManager(this, 2)
        guild_selection_recycler_view.setHasFixedSize(true)
        guild_selection_recycler_view.adapter = adapter
    }
}
