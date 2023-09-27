package com.seallook.androidx.data.repository

import android.content.Intent
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInCredential

interface SignInClientRepository {
    suspend fun getSignInCredentialFromIntent(intent: Intent): SignInCredential

    suspend fun getBeginSignInResult(): BeginSignInResult
}
