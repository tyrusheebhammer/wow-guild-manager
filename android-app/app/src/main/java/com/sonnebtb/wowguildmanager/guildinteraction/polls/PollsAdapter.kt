package com.sonnebtb.wowguildmanager.guildinteraction.polls

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.guildinteraction.announcements.Announcement
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalendarEvent

class PollsAdapter(var context: Context?, var ref: CollectionReference) : RecyclerView.Adapter<PollViewHolder> (){
    var polls: ArrayList<Poll> = ArrayList()
    init {
        ref.addSnapshotListener { snapshot: QuerySnapshot?, exception: FirebaseFirestoreException? ->
            snapshot?.let {
                polls.clear()
                for (doc in it) {
                    polls.add(Poll.fromSnapshot(doc))
                }
            }
            notifyDataSetChanged()
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