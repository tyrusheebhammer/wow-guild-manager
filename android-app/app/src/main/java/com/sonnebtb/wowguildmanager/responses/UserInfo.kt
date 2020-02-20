package com.sonnebtb.wowguildmanager.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInfo(var sub: Int, var id: Int, var battletag: String) : Parcelable {
    companion object {
        const val USER_INFO_PARCEL = "user_info_parcel"
        const val USER_INFO_ID = "user_info_id"
        const val USER_INFO_SUB = "user_info_sub"
        const val USER_INFO_BATTLETAG = "user_info_battletag"

    }
}