package com.seallook.androidx.domain.usecase.counselorinfo.office

import com.seallook.androidx.data.repository.OfficeInfoRepository
import com.seallook.androidx.domain.model.OfficeInfoModel
import javax.inject.Inject

class SetOfficeInfoUseCase @Inject constructor(
    private val officeInfoRepository: OfficeInfoRepository,
) {
    suspend operator fun invoke(id: Int, info: OfficeInfoModel) {
        officeInfoRepository.setOfficeInfo(id, info.toInfo())
    }
}
