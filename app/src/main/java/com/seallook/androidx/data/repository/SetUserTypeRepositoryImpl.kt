package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.UserType
import com.seallook.androidx.data.remote.auth.SetUserTypeApiService
import javax.inject.Inject

class SetUserTypeRepositoryImpl @Inject constructor(
    private val setUserTypeApiService: SetUserTypeApiService,
) : SetUserTypeRepository {
    override suspend fun setUserType(user: UserType) {
        setUserTypeApiService.setUserType(user.toResponse())
    }
}
