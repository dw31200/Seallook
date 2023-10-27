package com.seallook.androidx.domain.usecase.auth

import com.seallook.androidx.data.repository.auth.UidRepository
import com.seallook.androidx.domain.model.UidModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetUidUseCase @Inject constructor(
    private val uidRepository: UidRepository,
) {
    suspend operator fun invoke(id: Int): UidModel? {
        return uidRepository.getItem(id)?.let { UidModel(it) }
    }
}
