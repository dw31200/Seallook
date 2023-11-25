package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.Profile
import java.util.Date

data class ProfileModel(
    val id: Int,
    val email: String,
    val name: String?,
    val gender: Int?,
    val birth: Date?,
    val timestamp: Date?,
    val userType: Int?,
) {
    fun toDataModel(): Profile {
        return Profile(
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
        operator fun invoke(profile: Profile): ProfileModel {
            return ProfileModel(
                id = profile.id,
                email = profile.email,
                name = profile.name,
                gender = profile.gender,
                birth = profile.birth,
                timestamp = profile.timestamp,
                userType = profile.userType,
            )
        }
    }
}
