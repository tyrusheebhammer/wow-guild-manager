package com.sonnebtb.wowguildmanager.guildinteraction.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.CollectionReference
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.buildFab
import com.sonnebtb.wowguildmanager.responses.Guild
import kotlinx.android.synthetic.main.fragment_calendar.view.*

class CalendarFragment(
    var listener: CalenderEventClickListener,
    var ref: CollectionReference,
    var guild: Guild
): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        view.events.adapter = CalendarAdapter(context, ref, guild=guild)
        view.events.layoutManager = LinearLayoutManager(context)
        buildFab(view) {listener.calendarFabClicked()}
        return view
    }
}

interface CalenderEventClickListener {
    fun calendarFabClicked()
}


