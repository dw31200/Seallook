package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.ProfileEntity
import com.seallook.androidx.data.remote.model.ProfileResponse
import java.util.Date

data class Profile(
    val id: Int,
    val email: String,
    val name: String,
    val gender: Int,
    val birth: Date,
    val timestamp: Date,
    val userType: Int,
) {
    fun toLocalModel(): ProfileEntity {
        return ProfileEntity(
            id = id,
            email = email,
            name = name,
            gender = gender,
            birth = birth,
            timestamp = timestamp,
            userType = userType,
        )
    }
    fun toRemoteModel(): ProfileResponse {
        return ProfileResponse(
            id = id,
            email = email,
            name = name,
            gender = gender,
            birth = birth,
            timestamp = timestamp,
            userType = userType,
        )
    }

    companion object {
        operator fun invoke(profileEntity: ProfileEntity): Profile {
            return Profile(
                id = profileEntity.id,
                email = profileEntity.email,
                name = profileEntity.name,
                gender = profileEntity.gender,
                birth = profileEntity.birth,
                timestamp = profileEntity.timestamp,
                userType = profileEntity.userType,
            )
        }
        operator fun invoke(profileResponse: ProfileResponse): Profile {
            return Profile(
                id = profileResponse.id,
                email = profileResponse.email,
                name = profileResponse.name,
                gender = profileResponse.gender,
                birth = profileResponse.birth,
                timestamp = profileResponse.timestamp,
                userType = profileResponse.userType,
            )
        }
    }
}
