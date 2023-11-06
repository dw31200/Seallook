package com.seallook.androidx.domain.usecase.counselorinfo.schedule

import com.seallook.androidx.data.repository.counselor.schedule.CounselingScheduleRepository
import com.seallook.androidx.domain.model.CounselingScheduleModel
import javax.inject.Inject

class GetFromFirebaseCounselingScheduleUseCase @Inject constructor(
    private val counselingScheduleRepository: CounselingScheduleRepository,
) {
    suspend operator fun invoke(email: String): List<CounselingScheduleModel> {
        return counselingScheduleRepository.getAllFromFirebase(email).map {
            CounselingScheduleModel(it)
        }
    }
}
