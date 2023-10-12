package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.OfficeInfoRepository
import com.seallook.androidx.domain.model.OfficeInfoModel
import javax.inject.Inject

class UpdateOfficeInfoUseCase @Inject constructor(
    private val officeInfoRepository: OfficeInfoRepository,
) {
    suspend operator fun invoke(info: OfficeInfoModel): Boolean {
        return runCatching {
            officeInfoRepository.updateOfficeInfo(info.toInfo())
        }.fold(
            onSuccess = { true },
            onFailure = { false },
        )
    }
}
