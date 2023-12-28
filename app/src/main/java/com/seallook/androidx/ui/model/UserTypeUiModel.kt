package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.UserTypeModel
import com.seallook.androidx.share.UserTypeOption

data class UserTypeUiModel(
    val email: String,
    val userType: UserTypeOption,
) {
    fun toDomainModel(): UserTypeModel {
        return UserTypeModel(
            email = email,
            userType = userType,
        )
    }

    companion object {
        operator fun invoke(userTypeModel: UserTypeModel): UserTypeUiModel {
            return UserTypeUiModel(
                email = userTypeModel.email,
                userType = userTypeModel.userType,
            )
        }
    }
}
