package com.seallook.androidx.domain.usecase.counselorinfo.basic

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.repository.counselor.basicinfo.CounselorInfoRepository
import com.seallook.androidx.domain.model.CounselorInfoModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetCounselorInfoUseCase @Inject constructor(
    private val counselorInfoRepository: CounselorInfoRepository,
) {
    suspend operator fun invoke(user: FirebaseUser): CounselorInfoModel? {
        return counselorInfoRepository.getItem(user)?.let { CounselorInfoModel(it) }
    }
}
