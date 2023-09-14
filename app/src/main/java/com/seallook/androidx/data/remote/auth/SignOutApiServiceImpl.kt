package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SignOutApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : SignOutApiService {
    override fun signOut() {
        auth.signOut()
    }
}
