package com.seallook.androidx.domain.usecase.counselorinfo.basic

import com.seallook.androidx.data.repository.counselor.basicinfo.CounselorInfoRepository
import com.seallook.androidx.domain.model.CounselorInfoModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetCounselorInfoListUseCase @Inject constructor(
    private val counselorInfoRepository: CounselorInfoRepository,
) {
    suspend operator fun invoke(): List<CounselorInfoModel> {
        return counselorInfoRepository.getAll().map {
            CounselorInfoModel(it)
        }
    }
}
