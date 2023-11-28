package com.seallook.androidx.domain.usecase.counselorinfo.basic

import com.seallook.androidx.data.repository.counselor.basicinfo.CounselorInfoRepository
import com.seallook.androidx.domain.model.CounselorInfoModel
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetCounselorInfoListUseCase @Inject constructor(
    private val counselorInfoRepository: CounselorInfoRepository,
) {
    operator fun invoke(): Flow<List<CounselorInfoModel>> {
        return counselorInfoRepository.getAll().map {
            it.map {
                CounselorInfoModel(it)
            }
        }
    }
}
