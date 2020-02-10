package com.sonnebtb.wowguildmanager.guildselection

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.guildinteraction.GuildInteractionActivity

class GuildAdapter(var context: Context) : RecyclerView.Adapter<GuildViewHolder>() {
    private val guilds = ArrayList<Guild>()
    override fun onCreateViewHolder(parent: ViewGroup, index: Int): GuildViewHolder {
        Log.d(Constants.TAG, "Creating GuildVH")
        val view = LayoutInflater.from(context).inflate(R.layout.guild_card, parent, false)
        return GuildViewHolder(view, this, context)
    }

    override fun getItemCount() = guilds.size

    override fun onBindViewHolder(viewHolder: GuildViewHolder, index: Int) {
        viewHolder.bind(guilds[index])
    }

    fun createDemoGuilds(){
        for(i in 0..7){
            var guild: Guild = Guild("Name " + i, "Realm " + i)
            guilds.add(i, guild)
            notifyItemInserted(i)
        }
    }

    fun selectGuild(position: Int){
        val guildIntent = Intent(context, GuildInteractionActivity::class.java)
        guildIntent.putExtra(Guild.EXTRA_GUILD_NAME, guilds[position].guildName)
        guildIntent.putExtra(Guild.EXTRA_GUILD_REALM, guilds[position].guildRealm)
        context.startActivity(guildIntent)
    }

}