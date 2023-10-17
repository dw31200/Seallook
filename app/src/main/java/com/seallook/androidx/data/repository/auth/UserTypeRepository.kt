package com.seallook.androidx.data.repository.auth

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.UserType

interface UserTypeRepository {
    suspend fun getItem(user: FirebaseUser?): UserType?

    suspend fun setItem(user: FirebaseUser?, type: UserType): Boolean?
}
