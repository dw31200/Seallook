package com.seallook.androidx.domain.usecase.counselorinfo.office

import com.seallook.androidx.data.repository.counselor.office.OfficeInfoRepository
import com.seallook.androidx.domain.model.OfficeInfoModel
import javax.inject.Inject

class UpdateOfficeInfoUseCase @Inject constructor(
    private val officeInfoRepository: OfficeInfoRepository,
) {
    suspend operator fun invoke(info: OfficeInfoModel): Boolean {
        return runCatching {
            officeInfoRepository.updateItem(info.toInfo())
        }.fold(
            onSuccess = { true },
            onFailure = { false },
        )
    }
}
