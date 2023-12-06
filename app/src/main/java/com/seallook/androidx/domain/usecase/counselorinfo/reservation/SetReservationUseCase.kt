package com.seallook.androidx.domain.usecase.counselorinfo.reservation

import com.seallook.androidx.data.repository.counselor.reservation.ReservationRepository
import com.seallook.androidx.domain.model.ReservationModel
import java.util.Date
import javax.inject.Inject

class SetReservationUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository,
) {
    suspend operator fun invoke(params: Params): Result<Unit> {
        if (params.id == null || params.counselorEmail == null || params.scheduleId == null || params.clientEmail == null || params.date == null || params.confirm == null) {
            return Result.failure(
                Error("param is null"),
            )
        }
        return runCatching {
            reservationRepository.set(
                ReservationModel(
                    params.id,
                    params.counselorEmail,
                    params.scheduleId,
                    params.clientEmail,
                    params.date,
                    params.confirm,
                ).toDataModel(),
            )
        }
    }

    data class Params(
        val id: String?,
        val counselorEmail: String?,
        val scheduleId: String?,
        val clientEmail: String?,
        val date: Date?,
        val confirm: Boolean?,
    )
}
