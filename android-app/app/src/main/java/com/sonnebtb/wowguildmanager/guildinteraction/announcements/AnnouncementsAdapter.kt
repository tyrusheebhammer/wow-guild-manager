package com.sonnebtb.wowguildmanager.guildinteraction.announcements

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sonnebtb.wowguildmanager.R

class AnnouncementsAdapter(var context: Context?) : RecyclerView.Adapter<AnnouncementViewHolder>() {
    var announcements: ArrayList<Announcement> = ArrayList()
    init {
        //Generating random members for testing purposes
        for (i in 0..10) {
//            announcements.add()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_event, parent, false)
        return AnnouncementViewHolder(view, this)
    }

    override fun getItemCount(): Int = announcements.size


    override fun onBindViewHolder(holder: AnnouncementViewHolder, position: Int) {
        holder.bind(announcements[position])
    }

}