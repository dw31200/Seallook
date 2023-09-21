package com.seallook.androidx.data.repository

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.UserType
import com.seallook.androidx.data.remote.auth.GetUserTypeApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserTypeRepositoryImpl @Inject constructor(
    private val getUserTypeApiService: GetUserTypeApiService,
) : GetUserTypeRepository {
    override fun getUserType(user: FirebaseUser?): Flow<UserType?> {
        return getUserTypeApiService.getUserType(user).map {
            it?.let { UserType(it) }
        }
    }
}
