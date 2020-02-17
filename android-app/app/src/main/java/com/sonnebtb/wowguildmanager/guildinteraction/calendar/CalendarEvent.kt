package com.sonnebtb.wowguildmanager.guildinteraction.calendar

import android.os.Parcelable
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime
import java.util.*

@Parcelize
data class CalendarEvent (
    var createDate: String? = null,
    var creator: String? = null,
    var date: String = "",
    var desc: String = "Description",
    var endDate: String? = null,
    var guild: String? = null,
    var startDate: String? = null,
    var title: String = "String"

): Parcelable {
    @get:Exclude var id = ""
    companion object {
        val START_DATE_KEY = "startDate"
        val END_DATE_KEY = "endDate"
        fun fromSnapshot(snapshot: DocumentSnapshot) : CalendarEvent{
            val calendarEvent = snapshot.toObject(CalendarEvent::class.java)!!
            calendarEvent.id = snapshot.id
            return calendarEvent
        }
    }
}