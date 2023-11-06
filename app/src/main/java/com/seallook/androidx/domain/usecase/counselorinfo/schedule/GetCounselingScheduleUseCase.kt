package com.seallook.androidx.domain.usecase.counselorinfo.schedule

import com.seallook.androidx.data.repository.counselor.schedule.CounselingScheduleRepository
import com.seallook.androidx.domain.model.CounselingScheduleModel
import javax.inject.Inject

class GetCounselingScheduleUseCase @Inject constructor(
    private val counselingScheduleRepository: CounselingScheduleRepository,
) {
    suspend operator fun invoke(email: String): List<CounselingScheduleModel> {
        return counselingScheduleRepository.getAll(email).map {
            CounselingScheduleModel(it)
        }
    }
}
