package com.seallook.androidx.domain.usecase.counselorinfo.office

import com.seallook.androidx.data.repository.counselor.office.OfficeCounselorEmailRepository
import com.seallook.androidx.domain.model.OfficeCounselorEmailModel
import dagger.Reusable
import java.lang.Error
import javax.inject.Inject

@Reusable
class SetOfficeCounselorEmailUseCase @Inject constructor(
    private val officeCounselorEmailRepository: OfficeCounselorEmailRepository,
) {
    suspend operator fun invoke(params: Params): Result<Unit> {
        if (params.id == null || params.officeId == null || params.counselorEmail == null) return Result.failure(Error("params is null"))
        val list = officeCounselorEmailRepository.getList(params.officeId, params.counselorEmail)
        return runCatching {
            if (list.isEmpty()) {
                officeCounselorEmailRepository.set(
                    OfficeCounselorEmailModel(
                        params.id,
                        params.officeId,
                        params.counselorEmail,
                    )
                        .toDataModel(),
                )
            }
        }
    }

    data class Params(
        val id: String?,
        val officeId: String?,
        val counselorEmail: String?,
    )
}
