package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class CurrentUserApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : CurrentUserApiService {
    override fun getCurrentUser(): Boolean {
        return auth.currentUser != null
    }
}