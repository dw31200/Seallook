package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.ProfileModel
import java.util.Date

data class ProfileUiModel(
    val id: Int,
    val email: String,
    val name: String?,
    val gender: Int?,
    val birth: Date?,
    val timestamp: Date?,
    val userType: Int?,
) {
    fun toDomainModel(): ProfileModel {
        return ProfileModel(
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
        operator fun invoke(profileModel: ProfileModel): ProfileUiModel {
            return ProfileUiModel(
                id = profileModel.id,
                email = profileModel.email,
                name = profileModel.name,
                gender = profileModel.gender,
                birth = profileModel.birth,
                timestamp = profileModel.timestamp,
                userType = profileModel.userType,
            )
        }
    }
}
