package com.sonnebtb.wowguildmanager.guildinteraction.calendar

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_event.view.*
import java.time.format.DateTimeFormatter

class CalendarViewHolder(itemView: View, var adapter: CalendarAdapter): RecyclerView.ViewHolder(itemView) {
    var eventTitle = itemView.event_date
    var eventDescription = itemView.event_description
    var eventDate = itemView.event_date

    fun bind(calendarEvent: CalendarEvent) {
        eventTitle.text = calendarEvent.title
        eventDescription.text = calendarEvent.desc
        var formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        eventDate.text = calendarEvent.date.format(formatter)
    }
}