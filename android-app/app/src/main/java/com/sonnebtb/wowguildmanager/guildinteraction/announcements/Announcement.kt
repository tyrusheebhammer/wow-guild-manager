package com.sonnebtb.wowguildmanager.guildinteraction.announcements

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Announcement(var title: String = "ANNOUNCEMENT TITLE", var description: String = "Announcement String", var date: LocalDateTime = LocalDateTime.now()) : Parcelable