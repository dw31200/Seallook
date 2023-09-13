package com.seallook.androidx.domain.usecase

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.seallook.androidx.data.repository.BeginSignInResultRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class getBeginSignInResultUseCase @Inject constructor(
    private val beginSignInResultRepository: BeginSignInResultRepository,
) {
    suspend operator fun invoke(): BeginSignInResult {
        return beginSignInResultRepository.getBeginSignInResult()
    }
}