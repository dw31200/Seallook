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
}
