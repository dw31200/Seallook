package com.seallook.androidx.domain.usecase.counselorinfo.schedule

import com.seallook.androidx.data.repository.counselor.schedule.CounselingScheduleRepository
import com.seallook.androidx.domain.model.CounselingScheduleModel
import java.time.LocalDate
import javax.inject.Inject

class GetCounselingScheduleOnDateUseCase @Inject constructor(
    private val counselingScheduleRepository: CounselingScheduleRepository,
) {
    suspend operator fun invoke(selectedDate: LocalDate): List<CounselingScheduleModel> {
        return counselingScheduleRepository.getCounselingSchedulesOnDate(selectedDate).map {
            CounselingScheduleModel(it)
        }
    }
}
