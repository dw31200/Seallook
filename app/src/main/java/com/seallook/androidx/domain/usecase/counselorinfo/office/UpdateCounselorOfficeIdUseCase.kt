package com.seallook.androidx.domain.usecase.counselorinfo.office

import com.seallook.androidx.data.repository.counselor.office.CounselorOfficeIdRepository
import com.seallook.androidx.domain.model.CounselorOfficeIdModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UpdateCounselorOfficeIdUseCase @Inject constructor(
    private val counselorOfficeIdRepository: CounselorOfficeIdRepository,
) {
    suspend operator fun invoke(params: Params): Result<Unit> {
        if (params.email == null || params.officeId == null) return Result.failure(Error("email or officeId is null"))
        return runCatching {
            counselorOfficeIdRepository.update(
                CounselorOfficeIdModel(
                    params.email,
                    params.officeId,
                )
                    .toDataModel(),
            )
        }
    }

    data class Params(
        val email: String?,
        val officeId: String?,
    )
}
