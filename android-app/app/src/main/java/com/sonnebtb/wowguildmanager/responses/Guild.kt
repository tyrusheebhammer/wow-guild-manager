package com.sonnebtb.wowguildmanager.responses

import android.os.Parcelable
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Guild(
    var compound: String = "",
    var name: String = "",
    var realm: String = "",
    var characters: MutableList<Character> = ArrayList()
) : Parcelable {
    @get:Exclude var id = ""
    companion object {
        val GUILD_PARCEL = "guild_parcel"
//        fun fromSnapshot(snapshot: DocumentSnapshot): Guild{
//            val g = snapshot.toObject(Guild::class.java)!!
//            g.id = snapshot.id
//            return g
//        }
    }
}