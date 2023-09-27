package com.seallook.androidx.data.repository

import android.content.Intent
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInCredential
import com.seallook.androidx.data.remote.auth.SignInClientApiService
import javax.inject.Inject

class SignInClientRepositoryImpl @Inject constructor(
    private val signInClientApiService: SignInClientApiService,
) : SignInClientRepository {
    override suspend fun getSignInCredentialFromIntent(intent: Intent): SignInCredential {
        return signInClientApiService.getSignInCredentialFromIntent(intent)
    }

    override suspend fun getBeginSignInResult(): BeginSignInResult {
        return signInClientApiService.getBeginSignInResult()
    }
}
