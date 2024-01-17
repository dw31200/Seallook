package com.seallook.androidx.domain.usecase.counselorinfo.schedule

import com.seallook.androidx.data.repository.counselor.schedule.CounselingScheduleRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class DeleteAllCounselingScheduleUseCase @Inject constructor(
    private val counselingScheduleRepository: CounselingScheduleRepository,
) {
    suspend operator fun invoke() {
        counselingScheduleRepository.deleteAll()
    }
}
