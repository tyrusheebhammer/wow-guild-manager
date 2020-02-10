package com.sonnebtb.wowguildmanager.guildinteraction.polls

import android.os.Parcelable
import com.google.firebase.firestore.DocumentSnapshot
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
    companion object {
        fun fromSnapshot(snapshot: DocumentSnapshot) : Poll {
            val poll = snapshot.toObject(Poll::class.java)!!
            return poll
        }
    }
}
