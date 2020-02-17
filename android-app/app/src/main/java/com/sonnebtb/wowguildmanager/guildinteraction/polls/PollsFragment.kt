package com.sonnebtb.wowguildmanager.guildinteraction.polls

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.CollectionReference
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.buildFab
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalendarAdapter
import kotlinx.android.synthetic.main.fragment_calendar.view.*
import kotlinx.android.synthetic.main.fragment_polls.view.*

class PollsFragment(var listener: PollsEventClickListener, var ref: CollectionReference): Fragment() {
    private lateinit var thiscontext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_polls, container, false)
        view.polls.adapter = PollsAdapter(context, ref)
        view.polls.layoutManager = LinearLayoutManager(context)
        buildFab(view) {listener.pollFabClicked()}
        return view
    }
}

interface PollsEventClickListener {
    fun pollFabClicked()
}