package com.sonnebtb.wowguildmanager.guildinteraction.polls

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Poll(
    var creator: String = "Creator",
    var createDate: LocalDateTime  = LocalDateTime.now(),
    var guild: String? = null,
    var desc: String = "Description",
    var link: String? = null,
    var title: String = "Title",
    var validDate: LocalDateTime? = LocalDateTime.MAX
): Parcelable
