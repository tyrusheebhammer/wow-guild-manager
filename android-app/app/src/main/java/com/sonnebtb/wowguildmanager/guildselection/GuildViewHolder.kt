package com.sonnebtb.wowguildmanager.guildselection

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.sonnebtb.wowguildmanager.guildselection.GuildAdapter
import kotlinx.android.synthetic.main.guild_card.view.*

class GuildViewHolder: RecyclerView.ViewHolder {
    val guildNameTextView: TextView = itemView.guild_name_text_view
    val guildRealmTextView: TextView = itemView.guild_realm_text_view
    var cardView: CardView = itemView.guild_card_view
    lateinit var context: Context

    constructor(itemView: View, adapter: GuildAdapter, context: Context): super(itemView) {
        this.context = context
        itemView.setOnClickListener{
            //adapter.showAddDialog(adapterPosition)
        }
        itemView.setOnLongClickListener{
            //adapter.selectMovieQuote(adapterPosition)
            true
        }
    }
}