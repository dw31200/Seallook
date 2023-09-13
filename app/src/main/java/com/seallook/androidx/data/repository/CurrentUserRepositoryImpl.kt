package com.seallook.androidx.data.repository

import com.seallook.androidx.data.remote.CurrentUserApiService
import javax.inject.Inject

class CurrentUserRepositoryImpl @Inject constructor(
    private val currentUserApiService: CurrentUserApiService,
) : CurrentUserRepository {
    override fun getCurrentUser(): Boolean {
        return currentUserApiService.getCurrentUser()
    }
}