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

    private val _progressMessage = MutableLiveData<String>()
    val progressMessage: LiveData<String>
        get() = _progressMessage

    private val _isShowProgress = MutableLiveData<Boolean>()
    val isShowProgress: LiveData<Boolean>
        get() = _isShowProgress

    private val _isShowFailMessage = MutableLiveData<String>()
    val isShowFailMessage: LiveData<String>
        get() = _isShowFailMessage

    init {
        _progressMessage.value = "업데이트 중입니다."
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
            _isShowProgress.value = true
            updateReservedClientConfirmUseCase(id, confirm)
                .onSuccess {
                    _isShowProgress.value = false
                    fetchData()
                }
                .onFailure {
                    _isShowProgress.value = false
                    _isShowFailMessage.value = "업데이트 실패했습니다."
                }
        }
    }
}
