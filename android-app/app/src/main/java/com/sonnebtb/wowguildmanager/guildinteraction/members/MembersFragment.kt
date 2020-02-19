package com.sonnebtb.wowguildmanager.guildinteraction.members

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sonnebtb.wowguildmanager.R
import com.sonnebtb.wowguildmanager.responses.MemberCharacter

class MembersFragment(
    var guildMembers: MutableList<MemberCharacter>
): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_members, container, false) as RecyclerView
        val adapter = MembersAdapter(context, guildMembers)
        view.adapter = adapter
        view.layoutManager = LinearLayoutManager(context)

        return view
    }
}