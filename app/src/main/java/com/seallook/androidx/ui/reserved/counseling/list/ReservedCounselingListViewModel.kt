package com.seallook.androidx.ui.reserved.counseling.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import com.seallook.androidx.base.Effect
import com.seallook.androidx.domain.usecase.reserved.GetReservedCounselingListSnapshotUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.ReservationUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ReservedCounselingListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getReservedCounselingListSnapshotUseCase: GetReservedCounselingListSnapshotUseCase,
) : BaseViewModel<Effect>() {
    var email = savedStateHandle.get<String>("email")

    val reservedCounselingList: LiveData<List<ReservationUiModel>>? =
        email?.let {
            getReservedCounselingListSnapshotUseCase(it).map {
                it.map {
                    ReservationUiModel(it)
                }
            }.asLiveData()
        }
}
