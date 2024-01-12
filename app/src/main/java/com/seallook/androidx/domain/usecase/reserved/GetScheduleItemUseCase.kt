package com.seallook.androidx.domain.usecase.reserved

import com.seallook.androidx.domain.model.CounselingScheduleModel
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@Reusable
class GetScheduleItemUseCase @Inject constructor(
    private val getReservationUseCase: GetReservationUseCase,
    private val getCounselingScheduleUseCase: GetCounselingScheduleUseCase,
) {
    operator fun invoke(id: String?): Flow<CounselingScheduleModel?> {
        return if (id == null) {
            flow { emit(null) }
        } else {
            getReservationUseCase(id)
                .flatMapConcat { reservation ->
                    getCounselingScheduleUseCase(reservation?.scheduleId)
                }
        }
    }
}
