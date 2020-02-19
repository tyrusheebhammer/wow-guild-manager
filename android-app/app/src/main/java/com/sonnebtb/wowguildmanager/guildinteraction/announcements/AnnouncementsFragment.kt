package com.sonnebtb.wowguildmanager.guildinteraction.announcements

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.CollectionReference
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.buildFab
import kotlinx.android.synthetic.main.fragment_announcements.view.*
import kotlinx.android.synthetic.main.fragment_calendar.view.*

class AnnouncementsFragment(var listener: AnnouncementClickListener, var ref: CollectionReference, var guildID: String): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_announcements, container, false)
        buildFab(view) {listener.announcementFabClicked()}
        view.announcements.adapter = AnnouncementsAdapter(context, ref, guildID)
        view.announcements.layoutManager = LinearLayoutManager(context)
        return view
    }
}

interface AnnouncementClickListener {
    fun announcementFabClicked()
}