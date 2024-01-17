package com.seallook.androidx.domain.usecase.reserved

import com.seallook.androidx.data.repository.counselor.reservation.ReservationRepository
import com.seallook.androidx.domain.model.ReservationModel
import com.seallook.androidx.share.UserTypeOption
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetReservationListUseCase @Inject constructor(
    private val reservationRepository: ReservationRepository,
) {
    operator fun invoke(params: Params): Flow<List<ReservationModel>> {
        if (params.email == null || params.userType == null) return flow { emit(emptyList()) }
        return reservationRepository.getList(params.email, params.userType).map {
            it.map {
                ReservationModel(it)
            }
        }
    }

    data class Params(
        val email: String?,
        val userType: UserTypeOption?,
    )
}
