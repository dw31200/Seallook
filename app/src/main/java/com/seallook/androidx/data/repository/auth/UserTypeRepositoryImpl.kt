package com.seallook.androidx.data.repository.auth

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.UserType
import com.seallook.androidx.data.remote.auth.UserTypeApiService
import javax.inject.Inject

class UserTypeRepositoryImpl @Inject constructor(
    private val userTypeApiService: UserTypeApiService,
) : UserTypeRepository {
    override suspend fun getItem(user: FirebaseUser?): UserType? {
        return userTypeApiService.getItem(user)?.let { UserType(it) }
    }

    override suspend fun setItem(user: FirebaseUser?, type: UserType): Boolean? {
        return userTypeApiService.setItem(user, type.toResponse())
    }
}
