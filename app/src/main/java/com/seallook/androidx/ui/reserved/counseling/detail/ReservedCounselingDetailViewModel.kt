package com.seallook.androidx.ui.reserved.counseling.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.usecase.GetProfileWithEmailUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.GetCounselorInfoUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.office.GetCounselorOfficeIdUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.office.GetOfficeInfoUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.schedule.GetFromFirebaseCounselingScheduleUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.schedule.InsertCounselingScheduleUseCase
import com.seallook.androidx.domain.usecase.reserved.GetReservationUseCase
import com.seallook.androidx.domain.usecase.reserved.GetScheduleItemUseCase
import com.seallook.androidx.domain.usecase.reserved.UpdateReservedClientConfirmUseCase
import com.seallook.androidx.domain.usecase.usertype.GetUserTypeUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import com.seallook.androidx.ui.model.CounselorInfoUiModel
import com.seallook.androidx.ui.model.CounselorOfficeIdUiModel
import com.seallook.androidx.ui.model.OfficeInfoUiModel
import com.seallook.androidx.ui.model.ProfileUiModel
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.model.UserTypeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
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
    private val getProfileWithEmailUseCase: GetProfileWithEmailUseCase,
    getUserTypeUseCase: GetUserTypeUseCase,
    private val updateReservedClientConfirmUseCase: UpdateReservedClientConfirmUseCase,
) : BaseViewModel<ReservedCounselingDetailEffect>(),
    ReservedCounselingDetailUpdateConfirm {
    private val reservationId = savedStateHandle.get<String>("reservationId")

    val userType: LiveData<UserTypeUiModel?> = getUserTypeUseCase().map {
        it?.let {
            UserTypeUiModel(it)
        }
    }.asLiveData()

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

    val clientInfo: LiveData<ProfileUiModel> =
        reservationItem.switchMap {
            liveData {
                getProfileWithEmailUseCase(
                    GetProfileWithEmailUseCase.Params(
                        it?.clientEmail,
                    ),
                ).map {
                    ProfileUiModel(it)
                }
                    .first()
                    .let { emit(it) }
            }
        }

    val officeId: LiveData<CounselorOfficeIdUiModel?> =
        reservationItem.asFlow().flatMapLatest {
            getCounselorOfficeIdUseCase(
                GetCounselorOfficeIdUseCase.Params(
                    it?.counselorEmail,
                ),
            ).map {
                it?.let {
                    CounselorOfficeIdUiModel(it)
                }
            }
        }.asLiveData()

    val officeInfo: LiveData<OfficeInfoUiModel?> =
        officeId.asFlow().flatMapLatest {
            getOfficeInfoUseCase(
                GetOfficeInfoUseCase.Params(
                    it?.officeId,
                ),
            ).map {
                it?.let {
                    OfficeInfoUiModel(it)
                }
            }
        }.asLiveData()

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

    override fun updateConfirm(id: String, confirm: Boolean) {
        viewModelScope.launch {
            setEffect(ReservedCounselingDetailEffect.UpdateConfirm)
            updateReservedClientConfirmUseCase(id, confirm)
                .onSuccess {
                    setEffect(ReservedCounselingDetailEffect.SuccessUpdateConfirm)
                }
                .onFailure {
                    setEffect(ReservedCounselingDetailEffect.FailureUpdateConfirm)
                }
        }
    }
}
