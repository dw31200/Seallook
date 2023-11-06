package com.seallook.androidx.ui.reserve.counseling

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.base.Effect
import com.seallook.androidx.domain.usecase.counselorinfo.schedule.GetCounselingScheduleUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.reserve.counseling.calendar.ReserveCounselingSelectDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ReserveCounselingViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCounselingScheduleUseCase: GetCounselingScheduleUseCase,
) : BaseViewModel<Effect>(), ReserveCounselingSelectDate {
    var email = savedStateHandle.get<String>("email")
    init {
        viewModelScope.launch {
//            sdw312 가져오기 테스트
            val schedule = email?.let { getCounselingScheduleUseCase(it) }
            Timber.d("$schedule")
        }
    }

    private val _selectedDate = MutableLiveData<LocalDate>()
    val selectedDate: LiveData<LocalDate>
        get() = _selectedDate

    override fun selectDate(date: LocalDate) {
        _selectedDate.value = date
    }
}
