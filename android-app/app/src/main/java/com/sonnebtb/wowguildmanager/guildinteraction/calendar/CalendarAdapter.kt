package com.sonnebtb.wowguildmanager.guildinteraction.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.guildinteraction.members.GuildMember
import com.sonnebtb.wowguildmanager.guildinteraction.members.MemberViewHolder
import com.sonnebtb.wowguildmanager.guildinteraction.members.generateRandomMember

class CalendarAdapter(var context: Context?) : RecyclerView.Adapter<CalendarViewHolder> (){
    var calendarEvents: ArrayList<CalendarEvent> = ArrayList()
    init {
        for(i in 0..10) {
            calendarEvents.add(CalendarEvent())
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_calendar_event, parent, false)
        return CalendarViewHolder(view, this)
    }

    override fun getItemCount(): Int = calendarEvents.size


    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(calendarEvents[position])
    }
}