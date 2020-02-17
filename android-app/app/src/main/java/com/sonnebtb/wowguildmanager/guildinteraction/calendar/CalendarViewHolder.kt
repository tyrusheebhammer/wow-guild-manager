package com.sonnebtb.wowguildmanager.guildinteraction.calendar

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_event.view.*
import java.time.format.DateTimeFormatter

class CalendarViewHolder: RecyclerView.ViewHolder {
    var eventTitle = itemView.event_title
    var eventDescription = itemView.event_description
    var eventDate = itemView.event_date

    constructor(itemView: View, adapter: CalendarAdapter): super(itemView){
        itemView.setOnLongClickListener{
            adapter.remove(adapterPosition)
            true
        }
    }

    fun bind(calendarEvent: CalendarEvent) {
        eventTitle.text = calendarEvent.title
        eventDescription.text = calendarEvent.desc
        eventDate.text = calendarEvent.endDate
    }
}