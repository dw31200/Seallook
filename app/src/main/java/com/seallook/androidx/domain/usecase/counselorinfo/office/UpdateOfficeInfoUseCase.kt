package com.seallook.androidx.domain.usecase.counselorinfo.office

import com.seallook.androidx.data.repository.counselor.office.OfficeInfoRepository
import com.seallook.androidx.domain.model.OfficeInfoModel
import javax.inject.Inject

class UpdateOfficeInfoUseCase @Inject constructor(
    private val officeInfoRepository: OfficeInfoRepository,
) {
    suspend operator fun invoke(uid: String, info: OfficeInfoModel): Result<Unit> {
        return runCatching {
            officeInfoRepository.updateItem(uid, info.toDataModel())
        }
    }
}
