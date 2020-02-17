package com.sonnebtb.wowguildmanager.guildinteraction.announcements

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalendarAdapter
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalendarEvent
import kotlinx.android.synthetic.main.fragment_calendar.view.*

class AnnouncementsAdapter(var context: Context?, var ref: CollectionReference) : RecyclerView.Adapter<AnnouncementViewHolder>() {
    var announcements: ArrayList<Announcement> = ArrayList()
    init {
        ref.orderBy(Announcement.CREATE_DATE_KEY, Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot: QuerySnapshot?, exception: FirebaseFirestoreException? ->
            if(exception != null){
                Log.e(Constants.TAG, "Listen error: $exception")
                return@addSnapshotListener
            }
            for(docChange in snapshot!!.documentChanges){
                val announcement = Announcement.fromSnapshot(docChange.document)
                when (docChange.type){
                    DocumentChange.Type.ADDED -> {
                        announcements.add(0,announcement)
                        notifyItemInserted(0)
                    }
                    DocumentChange.Type.REMOVED -> {
                        val pos = announcements.indexOfFirst { announcement.id == it.id }
                        announcements.removeAt(pos)
                        notifyItemRemoved(pos)
                    }
                    DocumentChange.Type.MODIFIED -> {
                        val pos = announcements.indexOfFirst {announcement.id == it.id }
                        announcements[pos] = announcement
                        notifyItemChanged(pos)
                    }
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_event, parent, false)
        return AnnouncementViewHolder(view, this)
    }

    override fun getItemCount(): Int = announcements.size


    override fun onBindViewHolder(holder: AnnouncementViewHolder, position: Int) {
        holder.bind(announcements[position])
    }

    fun remove(position: Int){
        ref.document(announcements[position].id).delete()
    }

}