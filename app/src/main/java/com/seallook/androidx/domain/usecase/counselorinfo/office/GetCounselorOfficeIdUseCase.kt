package com.seallook.androidx.domain.usecase.counselorinfo.office

import com.seallook.androidx.data.repository.counselor.office.CounselorOfficeIdRepository
import com.seallook.androidx.domain.model.CounselorOfficeIdModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetCounselorOfficeIdUseCase @Inject constructor(
    private val counselorOfficeIdRepository: CounselorOfficeIdRepository,
) {
    suspend operator fun invoke(email: String?): Result<CounselorOfficeIdModel?> {
        if (email == null) return Result.failure(Error("email is null"))
        return runCatching {
            counselorOfficeIdRepository.getItem(email)?.let {
                CounselorOfficeIdModel(it)
            }
        }
    }
}
