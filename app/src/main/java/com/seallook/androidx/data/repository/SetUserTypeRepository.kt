package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.UserType

interface SetUserTypeRepository {
    suspend fun setUserType(user: UserType)
}
