package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.UserType
import com.seallook.androidx.share.UserTypeOption

data class UserTypeModel(
    val email: String,
    val userType: UserTypeOption,
) {
    fun toDataModel(): UserType {
        return UserType(
            email = email,
            userType = userType,
        )
    }

    companion object {
        operator fun invoke(userType: UserType): UserTypeModel {
            return UserTypeModel(
                email = userType.email,
                userType = userType.userType,
            )
        }
    }
}
