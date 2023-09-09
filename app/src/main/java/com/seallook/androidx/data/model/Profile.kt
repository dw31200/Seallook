package com.seallook.androidx.data.model

import com.seallook.androidx.data.remote.model.ProfileResponse
import com.seallook.androidx.share.GenderOption
import com.seallook.androidx.share.TypeOption
import java.util.Date

data class Profile(
    val key: String,
    val email: String,
    val nickname: String,
    val name: String,
    val gender: GenderOption,
    val birth: Date,
    val type: TypeOption,
    val timestamp: Date,
) {
    fun toResponse(): ProfileResponse {
        return ProfileResponse(
            key = key,
            email = email,
            nickname = nickname,
            name = name,
            gender = gender,
            birth = birth,
            type = type,
            timestamp = timestamp,
        )
    }

    companion object {
        operator fun invoke(profileResponse: ProfileResponse): Profile {
            return Profile(
                key = profileResponse.key,
                email = profileResponse.email,
                nickname = profileResponse.nickname,
                name = profileResponse.name,
                gender = profileResponse.gender,
                birth = profileResponse.birth,
                type = profileResponse.type,
                timestamp = profileResponse.timestamp,
            )
        }
    }
}
