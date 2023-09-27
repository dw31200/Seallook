package com.seallook.androidx.domain.usecase

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.seallook.androidx.data.repository.SignInClientRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetBeginSignInResultUseCase @Inject constructor(
    private val signInClientRepository: SignInClientRepository,
) {
    suspend operator fun invoke(): BeginSignInResult {
        return signInClientRepository.getBeginSignInResult()
    }
}
