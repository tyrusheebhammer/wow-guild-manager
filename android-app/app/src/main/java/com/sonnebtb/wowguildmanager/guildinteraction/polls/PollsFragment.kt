package com.sonnebtb.wowguildmanager.guildinteraction.polls

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sonnebtb.wowguildmanager.Constants
import com.sonnebtb.wowguildmanager.R
import kotlinx.android.synthetic.main.fab.*
import kotlinx.android.synthetic.main.fragment_polls.*

class PollsFragment: Fragment() {
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
                showAddDialog()
        }
        return view
    }

    fun showAddDialog() {
        val builder = AlertDialog.Builder(activity!!.applicationContext)
        //Set options
        builder.setTitle("New Guild Poll")

        // Content is message, view, or a list of items.
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_new_guild_poll, null, false)
        builder.setView(view)

        builder.setPositiveButton(android.R.string.ok){_, _ ->

        }
        builder.setNegativeButton(android.R.string.cancel, null)

        builder.create().show()
    }
}