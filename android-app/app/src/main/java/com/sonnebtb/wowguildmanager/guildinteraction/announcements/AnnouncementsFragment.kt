package com.sonnebtb.wowguildmanager.guildinteraction.announcements

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.buildFab

class AnnouncementsFragment(var listener: AnnouncementClickListener): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_announcements, container, false)
        buildFab(view) {listener.announcementFabClicked()}
        return view
    }
}

interface AnnouncementClickListener {
    fun announcementFabClicked()
}