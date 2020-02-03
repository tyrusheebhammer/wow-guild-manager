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
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.R

class CalendarFragment(var listener: CalenderEventClickListener): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false) as RecyclerView
        val adapter = CalendarAdapter(context)
        view.adapter = adapter
        view.layoutManager = LinearLayoutManager(context)
        buildFab(view, listener)
        return view
    }
    fun buildFab(root: View?, listener: CalenderEventClickListener) {
        val calendarFab = root?.findViewById<FloatingActionButton>(R.id.fab)
        calendarFab?.setOnClickListener {
            Log.d(Constants.TAG, "calendar fab clicked")
            listener.calendarFabClicked()
        }
    }


}

interface CalenderEventClickListener {
    fun calendarFabClicked()
}


