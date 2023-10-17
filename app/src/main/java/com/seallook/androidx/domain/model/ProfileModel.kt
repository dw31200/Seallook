package com.seallook.androidx.domain.model

import com.google.firebase.firestore.DocumentSnapshot
import com.seallook.androidx.data.model.Profile
import java.util.Date

data class ProfileModel(
    val key: String,
    val email: String,
    val name: String,
    val gender: Int,
    val birth: Date,
    val timestamp: Date,
) {
    fun toProfile(): Profile {
        return Profile(
            key = key,
            email = email,
            name = name,
            gender = gender,
            birth = birth,
            timestamp = timestamp,
        )
    }

    companion object {
        operator fun invoke(profile: Profile): ProfileModel {
            return ProfileModel(
                key = profile.key,
                email = profile.email,
                name = profile.name,
                gender = profile.gender,
                birth = profile.birth,
                timestamp = profile.timestamp,
            )
        }

        operator fun invoke(snapshot: DocumentSnapshot): ProfileModel {
            return ProfileModel(
                key = snapshot.id,
                email = snapshot.getString("email")!!,
                name = snapshot.getString("name")!!,
                gender = snapshot.getLong("gender")!!.toInt(),
                birth = snapshot.getDate("birth")!!,
                timestamp = snapshot.getDate("timestamp")!!,
            )
        }
    }
}
