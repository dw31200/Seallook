package com.seallook.androidx.domain.usecase.reserved

import com.seallook.androidx.data.repository.counselor.reservation.ReservationRepository
import com.seallook.androidx.domain.model.ReservationModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UpdateReservationUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository,
) {
    suspend operator fun invoke(reservation: ReservationModel) {
        reservationRepository.insert(reservation.toDataModel())
    }

    suspend operator fun invoke(reservationList: List<ReservationModel>) {
        reservationRepository.insert(
            reservationList
                .map {
                    it.toDataModel()
                },
        )
    }
}
