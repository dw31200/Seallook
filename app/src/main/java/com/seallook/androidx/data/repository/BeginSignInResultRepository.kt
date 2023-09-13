package com.seallook.androidx.data.repository

import com.google.android.gms.auth.api.identity.BeginSignInResult

interface BeginSignInResultRepository {
    suspend fun getBeginSignInResult(): BeginSignInResult
}