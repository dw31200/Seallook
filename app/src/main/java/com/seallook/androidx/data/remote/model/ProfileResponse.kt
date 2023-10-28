package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp
import com.seallook.androidx.base.RemoteModel
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
@IgnoreExtraProperties
data class ProfileResponse(
    val id: Int = 0,
    val birth: Date = Date(),
    val email: String = "",
    val gender: Int = 0,
    val name: String = "",
    @ServerTimestamp
    val timestamp: Date = Date(),
    val userType: Int = 0,
) : RemoteModel {

    fun check(): ProfileResponse? {
        return if (this == DEFAULT) {
            null
        } else {
            this
        }
    }

    companion object {
        private val DEFAULT = ProfileResponse()

        operator fun invoke(snapshot: DocumentSnapshot): ProfileResponse? {
            if (!snapshot.exists()) {
                return null
            }
            return snapshot.toObject(ProfileResponse::class.java)?.check()
        }
    }
}
