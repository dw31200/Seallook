package com.seallook.androidx.domain.usecase.reserved

import com.seallook.androidx.data.repository.counselor.reservation.ReservationRepository
import com.seallook.androidx.domain.model.ReservationModel
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetReservationUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository,
) {
    operator fun invoke(id: String?): Flow<ReservationModel?> {
        return if (id == null) {
            flow { emit(null) }
        } else {
            reservationRepository.getItem(id).map {
                it?.let {
                    ReservationModel(it)
                }
            }
        }
    }
}
