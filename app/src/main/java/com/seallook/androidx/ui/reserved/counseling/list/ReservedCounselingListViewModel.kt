package com.seallook.androidx.ui.reserved.counseling.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.usecase.reserved.GetReservationListUseCase
import com.seallook.androidx.domain.usecase.reserved.GetReservedListSnapshotUseCase
import com.seallook.androidx.domain.usecase.reserved.UpdateReservationUseCase
import com.seallook.androidx.domain.usecase.reserved.UpdateReservedClientConfirmUseCase
import com.seallook.androidx.domain.usecase.usertype.GetUserTypeUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.model.UserTypeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservedCounselingListViewModel @Inject constructor(
    private val getReservationListUseCase: GetReservationListUseCase,
    private val updateReservationUseCase: UpdateReservationUseCase,
    getUserTypeUseCase: GetUserTypeUseCase,
    private val getReservedListSnapshotUseCase: GetReservedListSnapshotUseCase,
    private val updateReservedClientConfirmUseCase: UpdateReservedClientConfirmUseCase,
) : BaseViewModel<ReservedClientListEffect>(),
    ReservedClientUpdateConfirm {
    val userType: LiveData<UserTypeUiModel?> = getUserTypeUseCase().map {
        it?.let { UserTypeUiModel(it) }
    }.asLiveData()

    val reservedCounselingList: LiveData<List<ReservationUiModel>> =
        userType.asFlow().flatMapLatest {
            getReservationListUseCase(
                GetReservationListUseCase.Params(
                    it?.email,
                    it?.userType,
                ),
            )
        }.map {
            it.map {
                ReservationUiModel(it)
            }
        }
            .asLiveData()

    init {
        userType.asFlow().flatMapLatest {
            getReservedListSnapshotUseCase(
                GetReservedListSnapshotUseCase.Params(
                    it?.email,
                    it?.userType,
                ),
            )
        }
            .onEach {
                updateReservationUseCase(it)
            }
            .launchIn(viewModelScope)
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
