package com.seallook.androidx.domain.usecase.counselorinfo.office

import com.seallook.androidx.data.repository.counselor.office.OfficeInfoRepository
import com.seallook.androidx.domain.model.OfficeInfoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetOfficeInfoUseCase @Inject constructor(
    private val officeInfoRepository: OfficeInfoRepository,
) {
    operator fun invoke(params: Params): Flow<OfficeInfoModel?> {
        return officeInfoRepository.getItem(params.id).map {
            it?.let {
                OfficeInfoModel(it)
            }
        }
    }

    data class Params(
        val _id: String?,
    ) {
        val id: String
            get() = _id ?: throw IllegalStateException("id is null")
    }
}
