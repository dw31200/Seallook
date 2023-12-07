package com.seallook.androidx.domain.usecase.reserved

import com.seallook.androidx.data.repository.counselor.reservation.ReservationRepository
import com.seallook.androidx.domain.model.ReservationModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetReservedCounselorListUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository,
) {
    suspend operator fun invoke(email: String): List<ReservationModel> {
        return reservationRepository.getCounselorList(email).map {
            ReservationModel(it)
        }
    }
}
