package com.seallook.androidx.domain.usecase

import android.content.Intent
import com.google.android.gms.auth.api.identity.SignInCredential
import com.seallook.androidx.data.repository.SignInClientRepository
import javax.inject.Inject

class GetSignInCredentialFromIntentUseCase @Inject constructor(
    private val signInClientRepository: SignInClientRepository,
) {
    suspend operator fun invoke(intent: Intent): SignInCredential {
        return signInClientRepository.getSignInCredentialFromIntent(intent)
    }
}
