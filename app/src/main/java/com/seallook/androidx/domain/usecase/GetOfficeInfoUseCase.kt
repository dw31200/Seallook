package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.OfficeInfoRepository
import com.seallook.androidx.domain.model.OfficeInfoModel
import javax.inject.Inject

class GetOfficeInfoUseCase @Inject constructor(
    private val officeInfoRepository: OfficeInfoRepository,
) {
    suspend operator fun invoke(id: Int): OfficeInfoModel? {
        return officeInfoRepository.getOfficeInfo(id)?.let { OfficeInfoModel(it) }
    }
}
