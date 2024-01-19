package com.seallook.androidx.ui.reserved.counseling.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.usecase.counselorinfo.schedule.GetFromFirebaseCounselingScheduleUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.schedule.InsertCounselingScheduleUseCase
import com.seallook.androidx.domain.usecase.reserved.GetReservationUseCase
import com.seallook.androidx.domain.usecase.reserved.GetScheduleItemUseCase
import com.seallook.androidx.domain.usecase.usertype.GetUserTypeUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.base.Effect
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.model.UserTypeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ReservedCounselingDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getReservationUseCase: GetReservationUseCase,
    private val getScheduleItemUseCase: GetScheduleItemUseCase,
    private val getFromFirebaseCounselingScheduleUseCase: GetFromFirebaseCounselingScheduleUseCase,
    private val insertCounselingScheduleUseCase: InsertCounselingScheduleUseCase,
    getUserTypeUseCase: GetUserTypeUseCase,
) : BaseViewModel<Effect>() {
    val reservationId = savedStateHandle.get<String>("reservationId")

    val userType: LiveData<UserTypeUiModel?> = getUserTypeUseCase().map {
        it?.let { UserTypeUiModel(it) }
    }.asLiveData()

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

    init {
        reservationItem.asFlow().flatMapLatest {
            flow {
                emit(
                    getFromFirebaseCounselingScheduleUseCase(
                        GetFromFirebaseCounselingScheduleUseCase.Params(
                            it?.counselorEmail,
                        ),
                    ),
                )
            }
        }
            .onEach {
                insertCounselingScheduleUseCase(it)
            }
            .launchIn(viewModelScope)
    }
}
