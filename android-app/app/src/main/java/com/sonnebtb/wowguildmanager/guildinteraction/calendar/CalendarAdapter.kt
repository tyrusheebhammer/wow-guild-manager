package com.sonnebtb.wowguildmanager.guildinteraction.calendar

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.R

class CalendarAdapter(var context: Context?, var ref: CollectionReference) : RecyclerView.Adapter<CalendarViewHolder> (){

    var calendarEvents: ArrayList<CalendarEvent> = ArrayList()


    init {
        ref.addSnapshotListener {snapshot: QuerySnapshot?, exception: FirebaseFirestoreException? ->
            snapshot?.let {
                calendarEvents.clear()
                for (doc in it) {
                    calendarEvents.add(CalendarEvent.fromSnapshot(doc))
                }
            }
            notifyDataSetChanged()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_event, parent, false)
        return CalendarViewHolder(view, this)
    }

    override fun getItemCount(): Int = calendarEvents.size


    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(calendarEvents[position])
    }
}