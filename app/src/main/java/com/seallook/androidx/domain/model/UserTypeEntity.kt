package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.UserType

data class UserTypeEntity(
    val usertype: Int,
) {
    fun toUserType(): UserType {
        return UserType(
            usertype = usertype,
        )
    }

    companion object {
        operator fun invoke(userType: UserType): UserTypeEntity {
            return UserTypeEntity(
                usertype = userType.usertype,
            )
        }
    }
}
