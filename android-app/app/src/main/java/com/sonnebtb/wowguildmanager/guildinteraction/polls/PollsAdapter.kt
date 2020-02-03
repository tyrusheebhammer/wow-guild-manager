package com.sonnebtb.wowguildmanager.guildinteraction.polls

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalendarEvent

class PollsAdapter(var context: Context?) : RecyclerView.Adapter<PollViewHolder> (){
    var polls: ArrayList<Poll> = ArrayList()
    init {
        for(i in 0..10) {
            polls.add(Poll())
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PollViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_poll, parent, false)
        return PollViewHolder(view, this)
    }

    override fun getItemCount(): Int = polls.size


    override fun onBindViewHolder(holder: PollViewHolder, position: Int) {
        holder.bind(polls[position])
    }
}