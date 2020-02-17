package com.sonnebtb.wowguildmanager.guildinteraction.announcements

import android.os.Parcelable
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import com.sonnebtb.wowguildmanager.guildinteraction.calendar.CalendarEvent
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Announcement(
    var createDate: String = "",
    var creator: String? = null,
    var desc: String = "Announcement String",
    var guild: String? = null,
    var title: String = "ANNOUNCEMENT TITLE"
) : Parcelable {
    @get:Exclude var id = ""
    companion object {
        val CREATE_DATE_KEY = "createDate"
        fun fromSnapshot(snapshot: DocumentSnapshot) : Announcement {
            val announcement = snapshot.toObject(Announcement::class.java)!!
            announcement.id = snapshot.id
            return announcement
        }
    }
}