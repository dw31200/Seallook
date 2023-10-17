package com.seallook.androidx.data.model

import com.seallook.androidx.data.remote.model.ProfileResponse
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

    companion object {
        operator fun invoke(profileResponse: ProfileResponse): Profile {
            return Profile(
                key = profileResponse.key,
                email = profileResponse.email,
                name = profileResponse.name,
                gender = profileResponse.gender,
                birth = profileResponse.birth,
                timestamp = profileResponse.timestamp,
            )
        }
    }
}
