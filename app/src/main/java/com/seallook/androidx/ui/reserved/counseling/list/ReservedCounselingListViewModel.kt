package com.seallook.androidx.ui.reserved.counseling.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.base.Effect
import com.seallook.androidx.domain.usecase.reserved.GetReservedCounselingListSnapshotUseCase
import com.seallook.androidx.domain.usecase.reserved.GetReservedCounselingListUseCase
import com.seallook.androidx.domain.usecase.reserved.UpdateReservationUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.ReservationUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ReservedCounselingListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getReservedCounselingListUseCase: GetReservedCounselingListUseCase,
    private val getReservedCounselingListSnapshotUseCase: GetReservedCounselingListSnapshotUseCase,
    private val updateReservationUseCase: UpdateReservationUseCase,
) : BaseViewModel<Effect>() {
    var email = savedStateHandle.get<String>("email")

    val reservedCounselingList: LiveData<List<ReservationUiModel>>? =
        email?.let {
            getReservedCounselingListUseCase(it).map {
                it.map {
                    ReservationUiModel(it)
                }
            }.asLiveData()
        }

    init {
        email?.let {
            getReservedCounselingListSnapshotUseCase(it)
                .onEach {
                    updateReservationUseCase(it)
                }
                .launchIn(viewModelScope)
        }
    }
}
