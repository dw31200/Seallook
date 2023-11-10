package com.seallook.androidx.ui.reserve.counseling

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.GetCounselingTypeLocalUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.GetCounselingTypeRemoteUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.SetCounselingTypeUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.reservation.SetReservationUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.schedule.GetCounselingScheduleOnDateUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.schedule.GetFromFirebaseCounselingScheduleUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.schedule.GetFromLocalCounselingScheduleUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.schedule.InsertCounselingScheduleUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.CounselingScheduleUiModel
import com.seallook.androidx.ui.model.CounselingTypeUiModel
import com.seallook.androidx.ui.model.ReservationUiModel
import com.seallook.androidx.ui.reserve.counseling.calendar.ReserveCounselingSelectDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ReserveCounselingViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getFromFirebaseCounselingScheduleUseCase: GetFromFirebaseCounselingScheduleUseCase,
    private val insertCounselingScheduleUseCase: InsertCounselingScheduleUseCase,
    private val getFromLocalCounselingScheduleUseCase: GetFromLocalCounselingScheduleUseCase,
    private val getCounselingScheduleOnDateUseCase: GetCounselingScheduleOnDateUseCase,
    private val getCounselingTypeRemoteUseCase: GetCounselingTypeRemoteUseCase,
    private val setCounselingTypeUseCase: SetCounselingTypeUseCase,
    private val getCounselingTypeLocalUseCase: GetCounselingTypeLocalUseCase,
    private val setReservationUseCase: SetReservationUseCase,
) : BaseViewModel<ReserveCounselingEffect>(), ReserveCounselingSelectDate, CounselingScheduleSelect {
    var email = savedStateHandle.get<String>("email")
    private val _counselingScheduleList = MutableLiveData<List<CounselingScheduleUiModel>>()
    val counselingScheduleList: LiveData<List<CounselingScheduleUiModel>>
        get() = _counselingScheduleList
    private val _counselingTypeList = MutableLiveData<List<CounselingTypeUiModel>>()
    val counselingTypeList: LiveData<List<CounselingTypeUiModel>>
        get() = _counselingTypeList
    private val _selectedDate = MutableLiveData<LocalDate>(LocalDate.now())
    val selectedDate: LiveData<LocalDate>
        get() = _selectedDate
    private val _selectedSchedulePrice = MutableLiveData<Int>()
    val selectedSchedulePrice: LiveData<Int>
        get() = _selectedSchedulePrice

    init {
        viewModelScope.launch {
//            sdw312 가져오기 테스트
            val schedule = email?.let { getFromFirebaseCounselingScheduleUseCase(it) }
            if (schedule != null) {
                insertCounselingScheduleUseCase(schedule)
            }
            selectedDate.value?.let { selectDate(it) }
            val type = email?.let { getCounselingTypeRemoteUseCase(it) }
            if (type != null) {
                setCounselingTypeUseCase(type)
            }
            _counselingTypeList.value = email?.let {
                getCounselingTypeLocalUseCase(it).map {
                    CounselingTypeUiModel(it)
                }
            }
        }
    }

    override fun selectDate(date: LocalDate) {
        viewModelScope.launch {
            _selectedDate.value = date
            _counselingScheduleList.value = getCounselingScheduleOnDateUseCase(date).map {
                CounselingScheduleUiModel(it)
            }
            _selectedSchedulePrice.value = 0
        }
    }

    override fun selectSchedule(price: Int) {
        _selectedSchedulePrice.value = price
    }

    fun setReservation() {
        viewModelScope.launch {
            setReservationUseCase(
//                sdw312 테스트용 더미 데이터
                ReservationUiModel(
                    1,
                    email ?: "",
                    1,
                    "dw31200@gmail.com",
                    false,
                ).toDomainModel(),
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
