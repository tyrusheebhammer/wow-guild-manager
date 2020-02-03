package com.sonnebtb.wowguildmanager.guildinteraction.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sonnebtb.wowguildmanager.R

class CalendarAdapter(var context: Context?) : RecyclerView.Adapter<PollViewHolder> (){
    var calendarEvents: ArrayList<CalendarEvent> = ArrayList()
    init {
        for(i in 0..10) {
            calendarEvents.add(CalendarEvent())
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PollViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_event, parent, false)
        return PollViewHolder(view, this)
    }

    override fun getItemCount(): Int = calendarEvents.size


    override fun onBindViewHolder(holder: PollViewHolder, position: Int) {
        holder.bind(calendarEvents[position])
    }
}