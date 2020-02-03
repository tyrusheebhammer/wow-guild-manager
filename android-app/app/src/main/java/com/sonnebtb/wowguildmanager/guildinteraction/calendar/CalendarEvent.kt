package com.sonnebtb.wowguildmanager.guildinteraction.calendar

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime
import java.util.*

@Parcelize
data class CalendarEvent (
    var createDate: LocalDateTime? = null,
    var creator: String? = null,
    var date: LocalDateTime = LocalDateTime.now(),
    var desc: String = "Description",
    var endDate: LocalDateTime? = null,
    var startDate: LocalDateTime? = null,
    var title: String = "Event"

): Parcelable