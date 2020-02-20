package com.sonnebtb.wowguildmanager.guildinteraction.members

import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.responses.MemberCharacter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_member.view.*

class MemberViewHolder(itemView: View, var adapter: MembersAdapter): RecyclerView.ViewHolder(itemView) {
    val characterName = itemView.character_name
    val characterRank = itemView.character_rank
    val characterLevel = itemView.character_level
    val characterRaiderIO = itemView.character_raider_io
    val characterItemLevel = itemView.character_item_level
    init {
        itemView.setOnClickListener{
            adapter.redirectToSite(adapterPosition)
        }
    }

    fun bind(mc: MemberCharacter) {
        characterName.text = mc.member.character.name

        adapter.context?.let {
            val classId = mc.member.character.playable_class.id
            val characterClass = CharacterClasses.idToClass(classId)
            val color = ContextCompat.getColor(it, characterClass.color)
//
            characterName.setTextColor(color)
        }


        characterItemLevel.text= "iLevel: 0"
        characterLevel.text = "Level: ${ mc.member.character.level}"
        characterRank.text = "Rank: ${mc.member.rank}"
        characterRaiderIO.text = "raider.io score: 0"

        Picasso.get()
            .load("https://render-us.worldofwarcraft.com/character/" + mc.character.thumbnail)
            .placeholder(R.drawable.battle_net_icon_9)
            .into(itemView.character_image)
    }
}