package com.seallook.androidx.domain.usecase.reserved

import com.seallook.androidx.data.repository.counselor.reservation.ReservationRepository
import com.seallook.androidx.domain.model.ReservationModel
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetReservedCounselingListSnapshotUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository,
) {
    operator fun invoke(email: String): Flow<List<ReservationModel>> {
        return reservationRepository.onCounselingListSnapshot(email).map {
            it.map {
                ReservationModel(it)
            }
        }
    }
}
