package com.sonnebtb.wowguildmanager.guildinteraction.members

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sonnebtb.wowguildmanager.R

class MembersAdapter(var context: Context?) : RecyclerView.Adapter<MemberViewHolder>() {
    var members: ArrayList<GuildMember> = ArrayList()
    init {
        //Generating random members for testing purposes
       for (i in 0..10) {
           members.add(generateRandomMember())
       }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_member, parent, false)
        return MemberViewHolder(view, this)
    }

    override fun getItemCount(): Int = members.size


    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(members[position])
    }

}