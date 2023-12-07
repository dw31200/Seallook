package com.seallook.androidx.ui.reserved.client.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.base.Effect
import com.seallook.androidx.domain.usecase.reserved.GetReservedClientListUseCase
import com.seallook.androidx.domain.usecase.reserved.UpdateReservedClientConfirmUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.ReservationUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservedClientListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getReservedClientListUseCase: GetReservedClientListUseCase,
    private val updateReservedClientConfirmUseCase: UpdateReservedClientConfirmUseCase,
) : BaseViewModel<Effect>(),
    ReservedClientUpdateConfirm {
    var email = savedStateHandle.get<String>("email")
    private val _reservedClientList = MutableLiveData<List<ReservationUiModel>>()
    val reservedClientList: LiveData<List<ReservationUiModel>>
        get() = _reservedClientList

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _reservedClientList.value = email?.let {
                getReservedClientListUseCase(it).map {
                    ReservationUiModel(it)
                }
            }
        }
    }

    override fun updateConfirm(id: String, confirm: Boolean) {
        viewModelScope.launch {
            updateReservedClientConfirmUseCase(id, confirm)
                .onSuccess {
                    fetchData()
                }
                .onFailure {
                    Unit
                }
        }
    }
}
