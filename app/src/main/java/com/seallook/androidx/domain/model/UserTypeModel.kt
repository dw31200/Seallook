package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.UserType

data class UserTypeModel(
    val usertype: Int,
) {
    fun toDataModel(): UserType {
        return UserType(
            usertype = usertype,
        )
    }

    companion object {
        operator fun invoke(userType: UserType): UserTypeModel {
            return UserTypeModel(
                usertype = userType.usertype,
            )
        }
    }
}
