package com.seallook.androidx.data.repository.auth

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.Profile

interface ProfileRepository {
    suspend fun getItem(user: FirebaseUser?): Profile?

    suspend fun setItem(user: FirebaseUser?, profile: Profile): Boolean?
}
