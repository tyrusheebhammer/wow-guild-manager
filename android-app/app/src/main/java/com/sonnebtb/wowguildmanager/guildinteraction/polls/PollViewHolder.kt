package com.sonnebtb.wowguildmanager.guildinteraction.polls

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalendarAdapter
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalendarEvent
import com.sonnebtb.wowguildmanager.guildselection.GuildAdapter
import kotlinx.android.synthetic.main.card_event.view.*
import java.time.format.DateTimeFormatter

class PollViewHolder(itemView: View, var adapter: PollsAdapter): RecyclerView.ViewHolder(itemView) {
//    var eventTitle = itemView.event_date
//    var eventDescription = itemView.event_description
//    var eventDate = itemView.event_date

    fun bind(poll: Poll) {
//        eventTitle.text = calendarEvent.title
//        eventDescription.text = calendarEvent.desc
//        var formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
//        eventDate.text = calendarEvent.date.format(formatter)
    }
}