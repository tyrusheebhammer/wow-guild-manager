package com.sonnebtb.wowguildmanager.guildinteraction.polls

import android.os.Parcelable
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Poll(
    var creator: String = "Creator",
    var createDate: String? = null,
    var guild: String? = null,
    var desc: String = "Description",
    var link: String? = null,
    var title: String = "Title",
    var validDate: String? = null
): Parcelable {
    @get:Exclude var id = ""
    companion object {
        val CREATE_DATE_KEY = "createDate"
        fun fromSnapshot(snapshot: DocumentSnapshot) : Poll {
            val poll = snapshot.toObject(Poll::class.java)!!
            poll.id = snapshot.id
            return poll
        }
    }
}
