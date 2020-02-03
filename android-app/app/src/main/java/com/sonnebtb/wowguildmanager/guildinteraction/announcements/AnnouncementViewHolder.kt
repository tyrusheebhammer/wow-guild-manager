package com.sonnebtb.wowguildmanager.guildinteraction.announcements

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_event.view.*
import java.time.format.DateTimeFormatter

class AnnouncementViewHolder(itemView: View, var adapter: AnnouncementsAdapter): RecyclerView.ViewHolder(itemView) {
    var title = itemView.event_title
    var description = itemView.event_description
    var date = itemView.event_date

    fun bind(announcement: Announcement) {
        title.text = announcement.title
        description.text = announcement.desc
        var formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        date.text = announcement.createDate.format(formatter)
    }
}