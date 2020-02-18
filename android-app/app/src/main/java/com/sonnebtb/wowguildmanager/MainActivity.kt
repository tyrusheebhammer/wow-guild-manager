package com.sonnebtb.wowguildmanager

import android.app.usage.ConfigurationStats
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.dementh.lib.battlenet_oauth2.BnConstants
import com.dementh.lib.battlenet_oauth2.activities.BnOAuthAccessTokenActivity
import com.dementh.lib.battlenet_oauth2.connections.BnOAuth2Params
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sonnebtb.wowguildmanager.BuildConfig.*
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


            val bnOAuth2Params = BnOAuth2Params(clientId, clientSecret, BnConstants.ZONE_UNITED_STATES, redirectUrl, Constants.APP_NAME, BnConstants.SCOPE_WOW)
            val oauthIntent = Intent(this, BnOAuthAccessTokenActivity::class.java)
            oauthIntent.putExtra(BnConstants.BUNDLE_BNPARAMS, bnOAuth2Params)
            oauthIntent.putExtra(BnConstants.BUNDLE_REDIRECT_ACTIVITY, GuildSelectionActivity::class.java)
            startActivity(oauthIntent)
        }
    }


}
