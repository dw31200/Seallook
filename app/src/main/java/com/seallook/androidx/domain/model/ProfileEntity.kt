package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.Profile
import java.util.Date

data class ProfileEntity(
    val key: String,
    val email: String,
    val name: String,
    val gender: Int,
    val birth: Date,
    val timestamp: Date,
) {
    fun toProfileModel(): Profile {
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
        operator fun invoke(profile: Profile): ProfileEntity {
            return ProfileEntity(
                key = profile.key,
                email = profile.email,
                name = profile.name,
                gender = profile.gender,
                birth = profile.birth,
                timestamp = profile.timestamp,
            )
        }
    }
}