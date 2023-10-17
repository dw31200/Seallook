package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.ProfileModel
import java.util.Date

data class ProfileUiModel(
    val key: String,
    val email: String,
    val name: String,
    val gender: Int,
    val birth: Date,
    val timestamp: Date,
) {
    fun toDomainModel(): ProfileModel {
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
        operator fun invoke(profileModel: ProfileModel): ProfileUiModel {
            return ProfileUiModel(
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
