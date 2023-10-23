package com.seallook.androidx.domain.usecase.counselorinfo.office

import com.seallook.androidx.data.repository.counselor.office.OfficeInfoRepository
import com.seallook.androidx.domain.model.OfficeInfoModel
import javax.inject.Inject

class GetAllOfficeInfoUseCase @Inject constructor(
    private val officeInfoRepository: OfficeInfoRepository,
) {
    suspend operator fun invoke(): List<OfficeInfoModel> {
        return officeInfoRepository.getAll().map { OfficeInfoModel(it) }
    }
}
