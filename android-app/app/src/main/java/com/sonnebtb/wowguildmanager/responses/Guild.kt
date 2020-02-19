package com.sonnebtb.wowguildmanager.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Guild(
    var compound: String = "",
    var name: String = "",
    var realm: String = "",
    var characters: MutableList<Character> = ArrayList()
) : Parcelable {
    companion object {
        val GUILD_PARCEL = "guild_parcel"
    }
}