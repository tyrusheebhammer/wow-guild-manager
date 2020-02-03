package com.sonnebtb.wowguildmanager.guildinteraction.announcements

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Announcement(
    var createDate: LocalDateTime = LocalDateTime.now(),
    var creator: String? = null,
    var desc: String = "Announcement String",
    var guild: String? = null,
    var title: String = "ANNOUNCEMENT TITLE"
) : Parcelable