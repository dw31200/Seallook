package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.UserTypeEntity
import com.seallook.androidx.share.UserTypeOption

data class UserType(
    val email: String,
    val userType: UserTypeOption,
) {
    fun toLocalModel(): UserTypeEntity {
        return UserTypeEntity(
            email = email,
            userTypeOption = userType,
        )
    }

    companion object {
        operator fun invoke(userTypeEntity: UserTypeEntity): UserType {
            return UserType(
                email = userTypeEntity.email,
                userType = userTypeEntity.userTypeOption,
            )
        }
    }
}
