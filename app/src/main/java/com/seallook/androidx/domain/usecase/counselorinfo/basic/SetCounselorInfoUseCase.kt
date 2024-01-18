package com.seallook.androidx.domain.usecase.counselorinfo.basic

import com.seallook.androidx.data.repository.counselor.basicinfo.CounselorInfoRepository
import com.seallook.androidx.domain.model.CounselorInfoModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SetCounselorInfoUseCase @Inject constructor(
    private val counselorInfoRepository: CounselorInfoRepository,
) {
    suspend operator fun invoke(params: Params): Result<Unit> {
        if (params.email == null || params.name == null || params.description == null || params.thumbnail == null) return Result.failure(Error("params is null"))
        return runCatching {
            counselorInfoRepository.setItem(
                CounselorInfoModel(
                    params.email,
                    params.name,
                    params.description,
                    params.thumbnail,
                )
                    .toDataModel(),
            )
        }
    }

    data class Params(
        val email: String?,
        val name: String?,
        val description: String?,
        val thumbnail: String?,
    )
}
