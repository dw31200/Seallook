package com.seallook.androidx.ui.reserved.counseling.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.base.Effect
import com.seallook.androidx.domain.usecase.reserved.GetReservedCounselingListUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.ReservationUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservedCounselingListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getReservedCounselingListUseCase: GetReservedCounselingListUseCase,
) : BaseViewModel<Effect>() {
    var email = savedStateHandle.get<String>("email")

    private val _reservedCounselingList = MutableLiveData<List<ReservationUiModel>>()
    val reservedCounselingList: LiveData<List<ReservationUiModel>>
        get() = _reservedCounselingList

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _reservedCounselingList.value = email?.let {
                getReservedCounselingListUseCase(it).map {
                    ReservationUiModel(it)
                }
            }
        }
    }
}
