package com.sonnebtb.wowguildmanager.guildinteraction.announcements

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Announcement(var title: String, var description: String, var date: LocalDateTime) : Parcelable