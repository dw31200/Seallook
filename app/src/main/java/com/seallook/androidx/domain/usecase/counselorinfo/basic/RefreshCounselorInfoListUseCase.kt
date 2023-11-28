package com.seallook.androidx.domain.usecase.counselorinfo.basic

import com.seallook.androidx.data.repository.counselor.basicinfo.CounselorInfoRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class RefreshCounselorInfoListUseCase @Inject constructor(
    private val counselorInfoRepository: CounselorInfoRepository,
) {
    suspend operator fun invoke() {
        counselorInfoRepository.refresh()
    }
}
