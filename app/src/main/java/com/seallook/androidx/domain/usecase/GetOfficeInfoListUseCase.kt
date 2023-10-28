package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.counselor.office.OfficeInfoRepository
import com.seallook.androidx.domain.model.OfficeInfoModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetOfficeInfoListUseCase @Inject constructor(
    private val officeInfoRepository: OfficeInfoRepository,
) {
    suspend operator fun invoke(params: Params): List<OfficeInfoModel> {
        return officeInfoRepository.getList(params.type, params.query).let {
            it.map {
                OfficeInfoModel(it)
            }
        }
    }

    data class Params(
        val type: String,
        val query: String,
    )
}
