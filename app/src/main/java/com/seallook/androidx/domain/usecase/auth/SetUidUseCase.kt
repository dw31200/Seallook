package com.seallook.androidx.domain.usecase.auth

import com.seallook.androidx.data.repository.auth.UidRepository
import com.seallook.androidx.domain.model.UidModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SetUidUseCase @Inject constructor(
    private val uidRepository: UidRepository,
) {
    suspend operator fun invoke(uidModel: UidModel) {
        uidRepository.insert(uidModel.toDataModel())
    }
}
