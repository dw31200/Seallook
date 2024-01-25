package com.seallook.androidx.ui.reserve.counseling

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.GetCounselorInfoUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.reservation.SetReservationUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.schedule.DeleteAllCounselingScheduleUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.schedule.GetCounselingScheduleOnDateUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.schedule.GetFromFirebaseCounselingScheduleUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.schedule.InsertCounselingScheduleUseCase
import com.seallook.androidx.domain.usecase.reserved.GetReservationListUseCase
import com.seallook.androidx.share.UserTypeOption
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import com.seallook.androidx.ui.model.CounselorInfoUiModel
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.reserve.counseling.calendar.ReserveCounselingSelectDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ReserveCounselingViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getFromFirebaseCounselingScheduleUseCase: GetFromFirebaseCounselingScheduleUseCase,
    private val insertCounselingScheduleUseCase: InsertCounselingScheduleUseCase,
    private val getCounselingScheduleOnDateUseCase: GetCounselingScheduleOnDateUseCase,
    private val setReservationUseCase: SetReservationUseCase,
    private val deleteAllCounselingScheduleUseCase: DeleteAllCounselingScheduleUseCase,
    private val getCounselorInfoUseCase: GetCounselorInfoUseCase,
    private val getReservationListUseCase: GetReservationListUseCase,
) : BaseViewModel<ReserveCounselingEffect>(), ReserveCounselingSelectDate, CounselingScheduleSelect {
    var email = savedStateHandle.get<String>("email")

    private val _clientEmail = MutableLiveData<String>()
    val clientEmail: LiveData<String>
        get() = _clientEmail

    val counselorInfo: LiveData<CounselorInfoUiModel> =
        liveData {
            getCounselorInfoUseCase(
                GetCounselorInfoUseCase.Params(
                    email,
                ),
            )?.let {
                emit(CounselorInfoUiModel(it))
            }
        }

    val scheduleList = MutableLiveData<List<CounselingScheduleUiModel>>()

    val reservationList: LiveData<List<ReservationUiModel>> =
        getReservationListUseCase(
            GetReservationListUseCase.Params(
                email,
                UserTypeOption.COUNSELOR,
            ),
        ).map {
            it.map {
                ReservationUiModel(it)
            }
        }.asLiveData()

    private val _scheduleOnDateList = MutableLiveData<List<CounselingScheduleUiModel>>()
    val scheduleOnDateList: LiveData<List<CounselingScheduleUiModel>>
        get() = _scheduleOnDateList

    private val _selectedDate = MutableLiveData(LocalDate.now())
    val selectedDate: LiveData<LocalDate>
        get() = _selectedDate

    private val _selectedSchedule = MutableLiveData<CounselingScheduleUiModel?>()
    val selectedSchedule: LiveData<CounselingScheduleUiModel?>
        get() = _selectedSchedule

    init {
        viewModelScope.launch {
            deleteAllCounselingScheduleUseCase()
            _clientEmail.value = getCurrentUserUseCase()?.email
            val schedule = email?.let {
                getFromFirebaseCounselingScheduleUseCase(
                    GetFromFirebaseCounselingScheduleUseCase.Params(
                        it,
                    ),
                )
            }
            if (schedule != null) {
                insertCounselingScheduleUseCase(schedule)
                scheduleList.value = schedule.map {
                    CounselingScheduleUiModel(it)
                }
            }
            selectedDate.value?.let { selectDate(it) }
        }
    }

    override fun selectDate(date: LocalDate) {
        viewModelScope.launch {
            _selectedDate.value = date
            _scheduleOnDateList.value = getCounselingScheduleOnDateUseCase(date.dayOfWeek).map {
                CounselingScheduleUiModel(it)
            }
            _selectedSchedule.value = null
        }
    }

    override fun selectSchedule(counselingScheduleItem: CounselingScheduleUiModel?) {
        _selectedSchedule.value = counselingScheduleItem
    }

    fun setReservation() {
        viewModelScope.launch {
            val instant = selectedDate.value?.atStartOfDay(ZoneId.systemDefault())?.toInstant()
            val date = Date.from(instant)
            val formatter = SimpleDateFormat("yyyy.MM.dd")
            setReservationUseCase(
                SetReservationUseCase.Params(
//                    sdw312 임시 id값
                    "",
                    email,
                    selectedSchedule.value?.id,
                    clientEmail.value,
                    formatter.format(date) + " " + selectedSchedule.value?.time,
                    false,
                ),
            )
                .onSuccess {
                    setEffect(ReserveCounselingEffect.NavigateToHome)
                }
                .onFailure {
                    Unit
                }
        }
    }
}
