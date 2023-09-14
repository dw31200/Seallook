package com.seallook.androidx.data.repository

import com.google.firebase.auth.AuthResult
import com.seallook.androidx.data.local.SignInSharedPreferences
import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.remote.auth.SignInApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val signInApiService: SignInApiService,
    private val signInSharedPreferences: SignInSharedPreferences,
) : SignInRepository {
    private val _profile = MutableStateFlow(signInSharedPreferences.getProfile()?.let { Profile(it) })

    override fun getProfile(): StateFlow<Profile?> = _profile

    override suspend fun cacheProfile(profile: Profile) {
        signInSharedPreferences.cacheProfile(profile.toProfileModel())
    }

    override suspend fun signInWithGoogle(token: String): AuthResult? {
        return signInApiService.signInWithGoogle(token)
    }
}
