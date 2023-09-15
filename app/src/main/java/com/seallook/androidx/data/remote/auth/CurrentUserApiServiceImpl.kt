package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrentUserApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : CurrentUserApiService {
    override fun getCurrentUser(): Flow<FirebaseUser?> {
        return flow { auth.currentUser }
    }
}
