package com.seallook.androidx.data.remote

import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BeginSignInResultApiServiceImpl @Inject constructor(
    private val oneTapClient: SignInClient,
    private val signInRequest: BeginSignInRequest,
) : BeginSignInResultApiService {
    override suspend fun getBeginSignInResult(): BeginSignInResult {
        return oneTapClient.beginSignIn(signInRequest).await()
    }
}