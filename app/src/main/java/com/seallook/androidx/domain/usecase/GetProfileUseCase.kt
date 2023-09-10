package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.DataCoroutine
import com.seallook.androidx.data.repository.SignInRepository
import com.seallook.androidx.domain.model.ProfileEntity
import dagger.Reusable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@Reusable
class GetProfileUseCase @Inject constructor(
    private val signInRepository: SignInRepository,
    @DataCoroutine private val externalScope: CoroutineScope,
) {
    operator fun invoke(): StateFlow<ProfileEntity?> {
        return signInRepository.getProfile().map {
            it?.let { ProfileEntity(it) }
        }.stateIn(
            externalScope,
            SharingStarted.WhileSubscribed(),
            null,
        )
    }
}