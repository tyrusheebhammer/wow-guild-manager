package com.sonnebtb.wowguildmanager.guildinteraction.members

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_member.view.*

class MemberViewHolder(itemView: View, var adapter: MembersAdapter): RecyclerView.ViewHolder(itemView) {
    val characterName = itemView.character_name
    val characterRank = itemView.character_rank
    val characterLevel = itemView.character_level
    val characterRaiderIO = itemView.character_raider_io
    val characterItemLevel = itemView.character_item_level
    init {

    }

    fun bind(member: GuildMember) {
        characterName.text = member.name

        adapter.context?.let {
            val color = ContextCompat.getColor(it, member.characterClass.color)

            characterName.setTextColor(color)
        }

        characterItemLevel.text= "iLevel: ${member.itemLevel}"
        characterLevel.text = "Level: ${member.level}"
        characterRank.text = "Rank: ${member.rank}"
        characterRaiderIO.text = "raider.io score: ${member.raiderIOScore}"
    }
}