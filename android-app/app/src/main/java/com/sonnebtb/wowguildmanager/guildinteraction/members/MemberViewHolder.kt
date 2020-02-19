package com.sonnebtb.wowguildmanager.guildinteraction.members

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sonnebtb.wowguildmanager.responses.MemberCharacter
import kotlinx.android.synthetic.main.card_member.view.*

class MemberViewHolder(itemView: View, var adapter: MembersAdapter): RecyclerView.ViewHolder(itemView) {
    val characterName = itemView.character_name
    val characterRank = itemView.character_rank
    val characterLevel = itemView.character_level
    val characterRaiderIO = itemView.character_raider_io
    val characterItemLevel = itemView.character_item_level
    init {

    }

    fun bind(mc: MemberCharacter) {
        characterName.text = mc.member.character.name

        adapter.context?.let {
//            val color = ContextCompat.getColor(it, member.characterClass.color)
//
//            characterName.setTextColor(color)
        }
        mc.member.rank

        characterItemLevel.text= "iLevel: 0"
        characterLevel.text = "Level: ${ mc.member.character.level}"
        characterRank.text = "Rank: ${mc.member.rank}"
        characterRaiderIO.text = "raider.io score: 0"
    }
}