package com.sonnebtb.wowguildmanager

import android.content.Context
import android.provider.SyncStateContract
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GuildAdapter(var context: Context) : RecyclerView.Adapter<GuildViewHolder>() {
    //private val guilds = ArrayList<Guild>()
    override fun onCreateViewHolder(parent: ViewGroup, index: Int): GuildViewHolder {
        //Log.d(Constants.TAG, "Creating VH")
        val view = LayoutInflater.from(context).inflate(R.layout.guild_card, parent, false)
        return GuildViewHolder(view, this, context)
    }

    //override fun getItemCount() = guilds.size
    override fun getItemCount() = 6

    override fun onBindViewHolder(viewHolder: GuildViewHolder, index: Int) {
        //viewHolder.bind(guilds[index])
    }

//    fun selectMovieQuote(position: Int){
//        guilds[position].isSelected = !guilds[position].isSelected
//        notifyItemChanged(position)
//    }

}