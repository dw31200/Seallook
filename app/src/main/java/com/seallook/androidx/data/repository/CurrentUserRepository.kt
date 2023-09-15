package com.seallook.androidx.data.repository

import com.google.firebase.auth.FirebaseUser

interface CurrentUserRepository {
    fun getCurrentUser(): FirebaseUser?
}