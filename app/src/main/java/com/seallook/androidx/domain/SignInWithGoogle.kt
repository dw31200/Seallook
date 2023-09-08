package com.seallook.androidx.domain

import com.seallook.androidx.data.repository.SignInRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SignInWithGoogle @Inject constructor(
    private val signInRepository: SignInRepository,
) {
    suspend fun invoke(token: String) = signInRepository.signInWithGoogle(token)
}