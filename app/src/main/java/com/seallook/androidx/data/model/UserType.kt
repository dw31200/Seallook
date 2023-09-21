package com.seallook.androidx.data.model

import com.seallook.androidx.data.remote.model.UserTypeResponse

data class UserType(
    val usertype: Int,
) {
    fun toResponse(): UserTypeResponse {
        return UserTypeResponse(
            usertype = usertype,
        )
    }

    companion object {
        operator fun invoke(userTypeResponse: UserTypeResponse): UserType {
            return UserType(
                usertype = userTypeResponse.usertype,
            )
        }
    }
}
