package com.seallook.androidx.data.remote.auth

import android.content.Intent
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignInClientApiServiceImpl @Inject constructor(
    private val oneTapClient: SignInClient,
    private val signInRequest: BeginSignInRequest,
) : SignInClientApiService {
    override suspend fun getSignInCredentialFromIntent(intent: Intent): SignInCredential {
        return oneTapClient.getSignInCredentialFromIntent(intent)
    }

    override suspend fun getBeginSignInResult(): BeginSignInResult {
        return oneTapClient.beginSignIn(signInRequest).await()
    }
}
