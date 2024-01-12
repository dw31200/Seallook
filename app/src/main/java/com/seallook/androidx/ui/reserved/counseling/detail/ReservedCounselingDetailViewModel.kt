package com.seallook.androidx.ui.reserved.counseling.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import com.seallook.androidx.base.Effect
import com.seallook.androidx.domain.usecase.reserved.GetReservationUseCase
import com.seallook.androidx.domain.usecase.reserved.GetScheduleItemUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import com.seallook.androidx.ui.model.ReservationUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ReservedCounselingDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getReservationUseCase: GetReservationUseCase,
    private val getScheduleItemUseCase: GetScheduleItemUseCase,
) : BaseViewModel<Effect>() {
    val reservationId = savedStateHandle.get<String>("reservationId")

    val reservationItem: LiveData<ReservationUiModel?> =
        reservationId.let {
            getReservationUseCase(it).map {
                it?.let {
                    ReservationUiModel(it)
                }
            }.asLiveData()
        }

    val counselingScheduleItem: LiveData<CounselingScheduleUiModel?> =
        reservationId.let {
            getScheduleItemUseCase(it).map {
                it?.let {
                    CounselingScheduleUiModel(it)
                }
            }.asLiveData()
        }
}
