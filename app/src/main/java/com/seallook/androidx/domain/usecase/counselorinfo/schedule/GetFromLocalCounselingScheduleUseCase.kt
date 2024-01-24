package com.seallook.androidx.domain.usecase.counselorinfo.schedule

import com.seallook.androidx.data.repository.counselor.schedule.CounselingScheduleRepository
import com.seallook.androidx.domain.model.CounselingScheduleModel
import javax.inject.Inject

class GetFromLocalCounselingScheduleUseCase @Inject constructor(
    private val counselingScheduleRepository: CounselingScheduleRepository,
) {
    suspend operator fun invoke(params: Params): List<CounselingScheduleModel> {
        return counselingScheduleRepository.getAllFromLocal(params.email).map {
            CounselingScheduleModel(it)
        }
    }

    data class Params(
        private val _email: String?,
    ) {
        val email: String
            get() = _email ?: throw IllegalStateException("email is null")
    }
}
