package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.counselor.office.OfficeInfoRepository
import com.seallook.androidx.domain.model.OfficeInfoModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetOfficeInfoListUseCase @Inject constructor(
    private val officeInfoRepository: OfficeInfoRepository,
) {
    suspend operator fun invoke(type: String, query: String): List<OfficeInfoModel> {
        return officeInfoRepository.getList(type, query).let {
            it.map {
                OfficeInfoModel(it)
            }
        }
    }
}
