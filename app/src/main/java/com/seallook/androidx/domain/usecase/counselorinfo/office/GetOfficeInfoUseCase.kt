package com.seallook.androidx.domain.usecase.counselorinfo.office

import com.seallook.androidx.data.repository.counselor.office.OfficeInfoRepository
import com.seallook.androidx.domain.model.OfficeInfoModel
import javax.inject.Inject

class GetOfficeInfoUseCase @Inject constructor(
    private val officeInfoRepository: OfficeInfoRepository,
) {
    suspend operator fun invoke(id: String): OfficeInfoModel? {
        return officeInfoRepository.getItem(id)?.let { OfficeInfoModel(it) }
    }
}
