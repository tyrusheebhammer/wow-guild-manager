package com.sonnebtb.wowguildmanager.responses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInfo(var sub: Int, var id: Int, var battletag: String) : Parcelable