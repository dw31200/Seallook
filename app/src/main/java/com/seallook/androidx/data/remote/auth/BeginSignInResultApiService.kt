package com.seallook.androidx.data.remote.auth

import com.google.android.gms.auth.api.identity.BeginSignInResult

interface BeginSignInResultApiService {
    suspend fun getBeginSignInResult(): BeginSignInResult
}
