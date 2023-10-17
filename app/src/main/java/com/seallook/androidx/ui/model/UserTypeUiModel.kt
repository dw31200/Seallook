package com.seallook.androidx.ui.model

import com.seallook.androidx.domain.model.UserTypeModel

data class UserTypeUiModel(
    val usertype: Int,
) {
    fun toDomainModel(): UserTypeModel {
        return UserTypeModel(
            usertype = usertype,
        )
    }

    companion object {
        operator fun invoke(userTypeModel: UserTypeModel): UserTypeUiModel {
            return UserTypeUiModel(
                usertype = userTypeModel.usertype,
            )
        }
    }
}
