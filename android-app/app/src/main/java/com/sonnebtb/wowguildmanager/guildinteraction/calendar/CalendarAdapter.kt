package com.sonnebtb.wowguildmanager.guildinteraction.calendar

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.guildinteraction.FirebaseDeleteDelegate
import com.sonnebtb.wowguildmanager.responses.Guild

class CalendarAdapter(
    var context: Context?,
    var ref: CollectionReference,
    var guild: Guild,
    var deleteDelegate: FirebaseDeleteDelegate
) :
    RecyclerView.Adapter<CalendarViewHolder>() {

    var calendarEvents: ArrayList<CalendarEvent> = ArrayList()


    init {
        ref.orderBy(CalendarEvent.START_DATE_KEY, Query.Direction.DESCENDING)
//            .orderBy(CalendarEvent.END_DATE_KEY, Query.Direction.DESCENDING)
            .whereEqualTo("guild", guild.compound)
            .addSnapshotListener { snapshot: QuerySnapshot?, exception: FirebaseFirestoreException? ->
                if (exception != null) {
                    Log.e(Constants.TAG, "Listen error: $exception")
                    return@addSnapshotListener
                }
                for (docChange in snapshot!!.documentChanges) {
                    val calEvent = CalendarEvent.fromSnapshot(docChange.document)
                    when (docChange.type) {
                        DocumentChange.Type.ADDED -> {
                            calendarEvents.add(0, calEvent)
                            notifyItemInserted(0)
                        }
                        DocumentChange.Type.REMOVED -> {
                            val pos = calendarEvents.indexOfFirst { calEvent.id == it.id }
                            calendarEvents.removeAt(pos)
                            notifyItemRemoved(pos)
                        }
                        DocumentChange.Type.MODIFIED -> {
                            val pos = calendarEvents.indexOfFirst { calEvent.id == it.id }
                            calendarEvents[pos] = calEvent
                            notifyItemChanged(pos)
                        }
                    }
                }
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

    fun remove(position: Int){
        if(deleteDelegate.userIsCreator(calendarEvents[position].creator!!)) {
            ref.document(calendarEvents[position].id).delete()
        } else {
            Log.e(Constants.TAG, "User is not creator")
        }
    }
}