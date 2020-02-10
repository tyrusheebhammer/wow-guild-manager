package com.sonnebtb.wowguildmanager.guildinteraction.calendar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.CollectionReference
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.buildFab
import kotlinx.android.synthetic.main.fragment_calendar.view.*

class CalendarFragment(var listener: CalenderEventClickListener, var ref: CollectionReference): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        view.events.adapter = CalendarAdapter(context, ref)
        view.events.layoutManager = LinearLayoutManager(context)
        buildFab(view) {listener.calendarFabClicked()}
        return view
    }
}

interface CalenderEventClickListener {
    fun calendarFabClicked()
}


