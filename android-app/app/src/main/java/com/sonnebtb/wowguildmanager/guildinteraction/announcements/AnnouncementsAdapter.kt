package com.sonnebtb.wowguildmanager.guildinteraction.announcements

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.guildinteraction.members.MemberViewHolder
import com.sonnebtb.wowguildmanager.guildinteraction.members.generateRandomMember

class AnnouncementsAdapter(var context: Context?) : RecyclerView.Adapter<MemberViewHolder>() {
    var announcements: ArrayList<Announcement> = ArrayList()
    init {
        //Generating random members for testing purposes
        for (i in 0..10) {
//            announcements.add()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout., parent, false)
        return MemberViewHolder(view, this)
    }

    override fun getItemCount(): Int = members.size


    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(members[position])
    }

}