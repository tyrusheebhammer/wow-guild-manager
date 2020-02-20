package com.sonnebtb.wowguildmanager.guildinteraction.polls

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.CollectionReference
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.buildFab
import com.sonnebtb.wowguildmanager.guildinteraction.FirebaseDeleteDelegate
import com.sonnebtb.wowguildmanager.responses.Guild
import kotlinx.android.synthetic.main.fragment_polls.view.*

class PollsFragment(
    var listener: PollsEventClickListener,
    var ref: CollectionReference,
    var guild: Guild,
    var deleteDelegate: FirebaseDeleteDelegate
): Fragment() {
    private lateinit var thiscontext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_polls, container, false)
        view.polls.adapter = PollsAdapter(context, ref, guild=guild, deleteDelegate=deleteDelegate)
        view.polls.layoutManager = LinearLayoutManager(context)
        buildFab(view) {listener.pollFabClicked()}
        return view
    }
}

interface PollsEventClickListener {
    fun pollFabClicked()
}