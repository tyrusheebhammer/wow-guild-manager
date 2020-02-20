package com.sonnebtb.wowguildmanager.guildinteraction.members

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.responses.BlizzardEndpointHelper
import com.sonnebtb.wowguildmanager.responses.MemberCharacter

class MembersAdapter(var context: Context?, var guildMembers: MutableList<MemberCharacter>) : RecyclerView.Adapter<MemberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_member, parent, false)
        return MemberViewHolder(view, this)
    }

    override fun getItemCount(): Int = guildMembers.size


    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.bind(guildMembers[position])
    }

    fun redirectToSite(adapterPosition: Int) {
        var mc = guildMembers[adapterPosition]
        var realm = mc.member.character.realm.slug
        var name = mc.member.character.name
        var uri: String = "https://worldofwarcraft.com/en-us/character/us/$realm/$name"
        var browserIntent =  Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        context?.startActivity(browserIntent)
    }

}