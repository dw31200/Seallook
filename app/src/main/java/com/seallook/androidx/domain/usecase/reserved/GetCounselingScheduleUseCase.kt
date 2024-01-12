package com.seallook.androidx.domain.usecase.reserved

import com.seallook.androidx.data.repository.counselor.schedule.CounselingScheduleRepository
import com.seallook.androidx.domain.model.CounselingScheduleModel
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetCounselingScheduleUseCase @Inject constructor(
    private val counselingScheduleRepository: CounselingScheduleRepository,
) {
    operator fun invoke(id: String?): Flow<CounselingScheduleModel?> {
        return if (id == null) {
            flow { emit(null) }
        } else {
            counselingScheduleRepository.getItem(id).map {
                it?.let {
                    CounselingScheduleModel(it)
                }
            }
        }
    }
}
