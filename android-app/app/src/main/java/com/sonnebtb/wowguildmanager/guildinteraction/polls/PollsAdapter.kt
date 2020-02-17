package com.sonnebtb.wowguildmanager.guildinteraction.polls

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalendarEvent

class PollsAdapter(var context: Context?, var ref: CollectionReference) : RecyclerView.Adapter<PollViewHolder> (){
    var polls: ArrayList<Poll> = ArrayList()
    init {
        ref.orderBy(Poll.CREATE_DATE_KEY, Query.Direction.DESCENDING)
//            .orderBy(CalendarEvent.END_DATE_KEY, Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot: QuerySnapshot?, exception: FirebaseFirestoreException? ->
                if (exception != null) {
                    Log.e(Constants.TAG, "Listen error: $exception")
                    return@addSnapshotListener
                }
                for (docChange in snapshot!!.documentChanges) {
                    val poll = Poll.fromSnapshot(docChange.document)
                    when (docChange.type) {
                        DocumentChange.Type.ADDED -> {
                            polls.add(0, poll)
                            notifyItemInserted(0)
                        }
                        DocumentChange.Type.REMOVED -> {
                            val pos = polls.indexOfFirst { poll.id == it.id }
                            polls.removeAt(pos)
                            notifyItemRemoved(pos)
                        }
                        DocumentChange.Type.MODIFIED -> {
                            val pos = polls.indexOfFirst { poll.id == it.id }
                            polls[pos] = poll
                            notifyItemChanged(pos)
                        }
                    }
                }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PollViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_poll, parent, false)
        return PollViewHolder(view, this)
    }

    override fun getItemCount(): Int = polls.size

    fun remove(position: Int){
        ref.document(polls[position].id).delete()
    }

    override fun onBindViewHolder(holder: PollViewHolder, position: Int) {
        holder.bind(polls[position])
    }


}