package com.sonnebtb.wowguildmanager.guildinteraction.polls

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.R

class PollsFragment(var listener: PollsEventClickListener): Fragment() {
    private lateinit var thiscontext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_polls, container, false)
        val poll_fab = view?.findViewById<FloatingActionButton>(R.id.fab)
        poll_fab?.setOnClickListener {
                Log.d(Constants.TAG, "poll fab clicked")
                listener.pollFabClicked()
        }
        return view
    }

}

interface PollsEventClickListener {
    fun pollFabClicked()
}