package com.seallook.androidx.ui.reserved.client.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.usecase.reserved.GetReservedClientListSnapshotUseCase
import com.seallook.androidx.domain.usecase.reserved.GetReservedClientListUseCase
import com.seallook.androidx.domain.usecase.reserved.UpdateReservationUseCase
import com.seallook.androidx.domain.usecase.reserved.UpdateReservedClientConfirmUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.ReservationUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservedClientListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getReservedClientListUseCase: GetReservedClientListUseCase,
    private val updateReservedClientConfirmUseCase: UpdateReservedClientConfirmUseCase,
    private val getReservedClientListSnapshotUseCase: GetReservedClientListSnapshotUseCase,
    private val updateReservationUseCase: UpdateReservationUseCase,
) : BaseViewModel<ReservedClientListEffect>(),
    ReservedClientUpdateConfirm {
    var email = savedStateHandle.get<String>("email")

    val reservedClientList: LiveData<List<ReservationUiModel>>? =
        email?.let {
            getReservedClientListUseCase(it).map {
                it.map {
                    ReservationUiModel(it)
                }
            }.asLiveData()
        }

    init {
        email?.let {
            getReservedClientListSnapshotUseCase(it)
                .onEach {
                    updateReservationUseCase(it)
                }
                .launchIn(viewModelScope)
        }
    }

    override fun updateConfirm(id: String, confirm: Boolean) {
        viewModelScope.launch {
            setEffect(ReservedClientListEffect.UpdateConfirm)
            updateReservedClientConfirmUseCase(id, confirm)
                .onSuccess {
                    setEffect(ReservedClientListEffect.SuccessUpdateConfirm)
                }
                .onFailure {
                    setEffect(ReservedClientListEffect.FailureUpdateConfirm)
                }
        }
    }
}
