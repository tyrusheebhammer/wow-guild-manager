package com.sonnebtb.wowguildmanager.guildinteraction.calendar

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime
import java.util.*

@Parcelize
data class CalendarEvent (var title: String = "Event", var description: String = "Description", var date: LocalDateTime = LocalDateTime.now()): Parcelable