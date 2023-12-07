package com.seallook.androidx.domain.usecase.reserved

import com.seallook.androidx.data.repository.counselor.reservation.ReservationRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UpdateReservedClientConfirmUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository,
) {
    suspend operator fun invoke(id: String, confirm: Boolean): Result<Unit> {
        return runCatching {
            reservationRepository.update(id, confirm)
        }
    }
}
