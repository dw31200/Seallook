package com.seallook.androidx.data.repository.usertype

import com.seallook.androidx.data.model.UserType
import kotlinx.coroutines.flow.Flow

interface UserTypeRepository {
    fun get(): Flow<UserType?>

    suspend fun update(userType: UserType)
}
