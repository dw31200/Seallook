package com.seallook.androidx.domain.usecase.counselorinfo.reservation

import com.seallook.androidx.data.repository.counselor.reservation.ReservationRepository
import com.seallook.androidx.domain.model.ReservationModel
import javax.inject.Inject

class SetReservationUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository,
) {
    suspend operator fun invoke(reservation: ReservationModel): Result<Unit> {
        return runCatching {
            reservationRepository.set(reservation.toDataModel())
        }
    }
}
