package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.ServerTimestamp
import com.seallook.androidx.share.Gender
import com.seallook.androidx.share.GenderOption
import com.seallook.androidx.share.TypeOption
import com.seallook.androidx.share.UserType
import org.json.JSONObject
import java.util.Date

@IgnoreExtraProperties
data class ProfileResponse(
    @get: Exclude
    val key: String,
    val email: String,
    val nickname: String,
    val name: String,
    val gender: GenderOption,
    val birth: Date,
    val type: TypeOption,
    @ServerTimestamp
    val timestamp: Date,
) {
    fun toJson() = JSONObject().apply {
        put("id", key)
        put("email", email)
        put("nickname", nickname)
        put("name", name)
        put("gender", gender.value.name)
        put("birth", birth.time)
        put("type", type.value.name)
        put("timestamp", timestamp.time)
    }
    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): ProfileResponse {
            return ProfileResponse(
                key = snapshot.id,
                email = snapshot.getString("email")!!,
                nickname = snapshot.getString("nickname")!!,
                name = snapshot.getString("name")!!,
                gender = GenderOption(Gender.valueOf(snapshot.getString("gender")!!)),
                birth = snapshot.getDate("birth")!!,
                type = TypeOption(UserType.valueOf(snapshot.getString("type")!!)),
                timestamp = snapshot.getDate("timestamp")!!,
            )
        }

        operator fun invoke(json: JSONObject): ProfileResponse {
            return ProfileResponse(
                key = json.getString("id"),
                email = json.getString("email"),
                nickname = json.getString("nickname"),
                name = json.getString("name"),
                gender = GenderOption(Gender.valueOf(json.getString("gender"))),
                birth = json.getLong("birth").let { Date(it) },
                type = TypeOption(UserType.valueOf(json.getString("type"))),
                timestamp = json.getLong("timestamp").let { Date(it) },
            )
        }
    }
}
