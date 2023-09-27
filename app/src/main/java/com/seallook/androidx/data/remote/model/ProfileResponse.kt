package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp
import org.json.JSONObject
import java.util.Date

@IgnoreExtraProperties
data class ProfileResponse(
    @get: Exclude
    val key: String,
    val email: String,
    val name: String,
    val gender: Int,
    val birth: Date,
    @ServerTimestamp
    val timestamp: Date,
) {
    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): ProfileResponse {
            return ProfileResponse(
                key = snapshot.id,
                email = snapshot.getString("email") ?: "",
                name = snapshot.getString("name") ?: "",
                gender = snapshot.getLong("gender")?.toInt() ?: 0,
                birth = snapshot.getDate("birth") ?: Date(),
                timestamp = snapshot.getDate("timestamp") ?: Date(),
            )
        }

        operator fun invoke(json: JSONObject): ProfileResponse {
            return ProfileResponse(
                key = json.getString("id"),
                email = json.getString("email"),
                name = json.getString("name"),
                gender = json.getInt("gender"),
                birth = Date(json.getLong("birth")),
                timestamp = Date(json.getLong("timestamp")),
            )
        }
    }
}
