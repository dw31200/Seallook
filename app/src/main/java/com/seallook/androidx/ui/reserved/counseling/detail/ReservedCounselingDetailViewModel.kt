package com.seallook.androidx.ui.reserved.counseling.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.usecase.counselorinfo.basic.GetCounselorInfoUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.office.GetCounselorOfficeIdUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.office.GetOfficeInfoUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.schedule.GetFromFirebaseCounselingScheduleUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.schedule.InsertCounselingScheduleUseCase
import com.seallook.androidx.domain.usecase.reserved.GetReservationUseCase
import com.seallook.androidx.domain.usecase.reserved.GetScheduleItemUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.base.Effect
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import com.seallook.androidx.ui.model.CounselorInfoUiModel
import com.seallook.androidx.ui.model.OfficeInfoUiModel
import com.seallook.androidx.ui.model.ReservationUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ReservedCounselingDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getReservationUseCase: GetReservationUseCase,
    getScheduleItemUseCase: GetScheduleItemUseCase,
    private val getFromFirebaseCounselingScheduleUseCase: GetFromFirebaseCounselingScheduleUseCase,
    private val insertCounselingScheduleUseCase: InsertCounselingScheduleUseCase,
    getCounselorInfoUseCase: GetCounselorInfoUseCase,
    private val getCounselorOfficeIdUseCase: GetCounselorOfficeIdUseCase,
    private val getOfficeInfoUseCase: GetOfficeInfoUseCase,
) : BaseViewModel<Effect>() {
    private val reservationId = savedStateHandle.get<String>("reservationId")

    val reservationItem: LiveData<ReservationUiModel?> =
        getReservationUseCase(reservationId).map {
            it?.let {
                ReservationUiModel(it)
            }
        }.asLiveData()

    val counselorInfo: LiveData<CounselorInfoUiModel> =
        reservationItem.switchMap {
            liveData {
                getCounselorInfoUseCase(
                    GetCounselorInfoUseCase.Params(
                        it?.counselorEmail,
                    ),
                )?.let {
                    emit(CounselorInfoUiModel(it))
                }
            }
        }

    val officeId: LiveData<String?> =
        reservationItem.switchMap {
            liveData {
                getCounselorOfficeIdUseCase(
                    it?.counselorEmail,
                )
                    .onSuccess {
                        emit(it?.officeId)
                    }
            }
        }

    val officeInfo: LiveData<OfficeInfoUiModel?> =
        officeId.switchMap {
            liveData {
                it?.let {
                    getOfficeInfoUseCase(it)?.let {
                        emit(OfficeInfoUiModel(it))
                    }
                }
            }
        }

    val counselingScheduleItem: LiveData<CounselingScheduleUiModel?> =
        getScheduleItemUseCase(reservationId).map {
            it?.let {
                CounselingScheduleUiModel(it)
            }
        }.asLiveData()

    init {
        reservationItem.asFlow().flatMapLatest {
            flow {
                emit(
                    insertCounselingScheduleUseCase(
                        getFromFirebaseCounselingScheduleUseCase(
                            GetFromFirebaseCounselingScheduleUseCase.Params(
                                it?.counselorEmail,
                            ),
                        ),
                    ),
                )
            }
        }
            .launchIn(viewModelScope)
    }
}
