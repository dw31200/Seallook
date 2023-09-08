package com.seallook.androidx.data.model

import com.google.firebase.firestore.DocumentSnapshot
import com.seallook.androidx.data.remote.model.ProfileResponse
import org.json.JSONObject
import java.util.Date

data class Profile(
    val key: String,
    val email: String,
    val name: String,
    val gender: Int,
    val birth: Date,
    val timestamp: Date,
) {
    fun toResponse(): ProfileResponse {
        return ProfileResponse(
            key = key,
            email = email,
            name = name,
            gender = gender,
            birth = birth,
            timestamp = timestamp,
        )
    }
    fun toJson() = JSONObject().apply {
        put("id", key)
        put("email", email)
        put("name", name)
        put("gender", gender)
        put("birth", birth.time)
        put("timestamp", timestamp.time)
    }
    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): Profile {
            return Profile(
                key = snapshot.id,
                email = snapshot.getString("email")!!,
                name = snapshot.getString("name")!!,
                gender = snapshot.getLong("gender")!!.toInt(),
                birth = snapshot.getDate("birth")!!,
                timestamp = snapshot.getDate("timestamp")!!,
            )
        }

        operator fun invoke(json: JSONObject): Profile {
            return Profile(
                key = json.getString("id"),
                email = json.getString("email"),
                name = json.getString("name"),
                gender = json.getInt("gender"),
                birth = json.getLong("birth").let { Date(it) },
                timestamp = json.getLong("timestamp").let { Date(it) },
            )
        }
    }
}
