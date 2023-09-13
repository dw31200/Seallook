package com.seallook.androidx.data.repository

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.seallook.androidx.data.remote.BeginSignInResultApiService
import javax.inject.Inject

class BeginSignInResultRepositoryImpl @Inject constructor(
    private val beginSignInResultApiService: BeginSignInResultApiService,
) : BeginSignInResultRepository {
    override suspend fun getBeginSignInResult(): BeginSignInResult {
        return beginSignInResultApiService.getBeginSignInResult()
    }
}