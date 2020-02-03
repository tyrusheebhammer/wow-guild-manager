package com.sonnebtb.wowguildmanager

import android.util.Log
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalenderEventClickListener

fun buildFab(root: View?, listener: () -> Unit) {
    val calendarFab = root?.findViewById<FloatingActionButton>(R.id.fab)
    calendarFab?.setOnClickListener{listener()}
}