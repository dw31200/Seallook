package com.seallook.androidx.domain.usecase.counselorinfo.schedule

import com.seallook.androidx.data.repository.counselor.schedule.CounselingScheduleRepository
import com.seallook.androidx.domain.model.CounselingScheduleModel
import javax.inject.Inject

class GetFromFirebaseCounselingScheduleUseCase @Inject constructor(
    private val counselingScheduleRepository: CounselingScheduleRepository,
) {
    suspend operator fun invoke(params: Params): List<CounselingScheduleModel> {
        if (params.email == null) return emptyList()
        return counselingScheduleRepository.getAllFromFirebase(params.email).map {
            CounselingScheduleModel(it)
        }
    }

    data class Params(
        val email: String?,
    )
}
