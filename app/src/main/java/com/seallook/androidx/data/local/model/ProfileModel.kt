package com.seallook.androidx.data.local.model

import org.json.JSONObject
import java.util.Date

data class ProfileModel(
    val key: String,
    val email: String,
    val name: String,
    val gender: Int,
    val birth: Date,
    val timestamp: Date,
) {
    fun toJson() = JSONObject().apply {
        put("id", key)
        put("email", email)
        put("name", name)
        put("gender", gender)
        put("birth", birth.time)
        put("timestamp", timestamp.time)
    }
    companion object {
        operator fun invoke(json: JSONObject): ProfileModel {
            return ProfileModel(
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
