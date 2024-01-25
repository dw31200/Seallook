package com.seallook.androidx.domain.usecase.counselorinfo.reservation

import com.seallook.androidx.data.repository.counselor.reservation.ReservationRepository
import com.seallook.androidx.domain.model.ReservationModel
import javax.inject.Inject

class SetReservationUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository,
) {
    suspend operator fun invoke(params: Params): Result<ReservationModel> {
        return reservationRepository
            .set(
                ReservationModel(
                    params.id,
                    params.counselorEmail,
                    params.scheduleId,
                    params.clientEmail,
                    params.date,
                    params.confirm,
                ).toDataModel(),
            )
            .map {
                ReservationModel(it)
            }
    }

    data class Params(
        val _id: String?,
        val _counselorEmail: String?,
        val _scheduleId: String?,
        val _clientEmail: String?,
        val _date: String?,
        val _confirm: Boolean?,
    ) {
        val id: String
            get() = _id ?: throw IllegalStateException("id is null")

        val counselorEmail: String
            get() = _counselorEmail ?: throw IllegalStateException("counselorEmail is null")

        val scheduleId: String
            get() = _scheduleId ?: throw IllegalStateException("scheduleId is null")

        val clientEmail: String
            get() = _clientEmail ?: throw IllegalStateException("clientEmail is null")

        val date: String
            get() = _date ?: throw IllegalStateException("date is null")

        val confirm: Boolean
            get() = _confirm ?: throw IllegalStateException("confirm is null")
    }
}
