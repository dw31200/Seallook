package com.seallook.androidx.data.repository

import com.seallook.androidx.data.remote.auth.SignOutApiService
import javax.inject.Inject

class SignOutRepositoryImpl @Inject constructor(
    private val signOutApiService: SignOutApiService,
) : SignOutRepository {
    override fun signOut() {
        signOutApiService.signOut()
    }
}
