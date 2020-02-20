package com.sonnebtb.wowguildmanager.guildinteraction.polls

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalendarAdapter
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalendarEvent
import com.sonnebtb.wowguildmanager.guildselection.GuildAdapter
import kotlinx.android.synthetic.main.card_event.view.*
import kotlinx.android.synthetic.main.card_poll.view.*
import kotlinx.android.synthetic.main.dialog_new_guild_poll.view.*
import java.time.format.DateTimeFormatter

class PollViewHolder: RecyclerView.ViewHolder {

        val dateRange = itemView.date_range
        val desc = itemView.description
        val title = itemView.title

    constructor(itemView: View, adapter: PollsAdapter): super(itemView){
        itemView.setOnLongClickListener{
            adapter.remove(adapterPosition)
            true
        }

        itemView.setOnClickListener {
            adapter.redirectToLink(adapterPosition)
        }
    }

    fun bind(poll: Poll) {
        desc.text = poll.desc
        title.text = poll.title
        dateRange.text = "${poll.createDate} - ${poll.validDate}"

//        eventTitle.text = calendarEvent.title
//        eventDescription.text = calendarEvent.desc
//        var formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
//        eventDate.text = calendarEvent.date.format(formatter)
    }

}