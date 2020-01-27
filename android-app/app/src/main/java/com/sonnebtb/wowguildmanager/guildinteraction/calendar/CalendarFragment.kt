package com.sonnebtb.wowguildmanager.guildinteraction.calendar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.R

class CalendarFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        val calendar_fab = view?.findViewById<FloatingActionButton>(R.id.fab)
        calendar_fab?.setOnClickListener {
            Log.d(Constants.TAG, "calendar fab clicked")
            //adapter.showAddDialog()
        }
        return view
    }
}