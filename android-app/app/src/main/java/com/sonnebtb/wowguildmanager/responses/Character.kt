package com.sonnebtb.wowguildmanager.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    var name: String="",
    var realm: String="",
    var battlegroup: String="",
    var _class: Int = 0,
    var race: Int = 0,
    var gender: Int = 0,
    var level: Int = 0,
    var achievementPoints: Int = 0,
    var thumbnail: String = "",
    var lastModified: Long = 0,
    var spec: Spec = Spec(),
    var guild: String = "",
    var guildRealm: String = "",
    var calcClass: String = "",
    var faction: Int = 0,
    var totalHonorableKills: Int = 0,
    var id: Int = 0
) : Parcelable

@Parcelize
data class Spec(
    var name: String = "",
    var role: String = "",
    var backgroundImage: String = "",
    var icon: String = "",
    var description: String = "",
    var order: Int = 0
) : Parcelable