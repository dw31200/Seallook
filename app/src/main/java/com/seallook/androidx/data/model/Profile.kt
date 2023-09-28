package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.ProfileModel
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
    fun exists() = key.isNotBlank()
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

    fun toProfileModel(): ProfileModel {
        return ProfileModel(
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

        operator fun invoke(profileModel: ProfileModel): Profile {
            return Profile(
                key = profileModel.key,
                email = profileModel.email,
                name = profileModel.name,
                gender = profileModel.gender,
                birth = profileModel.birth,
                timestamp = profileModel.timestamp,
            )
        }
    }
}
