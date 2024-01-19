package com.seallook.androidx.ui.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.seallook.androidx.domain.usecase.reserved.GetReservedClientListUseCase
import com.seallook.androidx.domain.usecase.reserved.GetReservedCounselingListUseCase
import com.seallook.androidx.domain.usecase.usertype.GetUserTypeUseCase
import com.seallook.androidx.share.UserTypeOption
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.base.Effect
import com.seallook.androidx.ui.model.ReservationUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getUserTypeUseCase: GetUserTypeUseCase,
    private val getReservedClientListUseCase: GetReservedClientListUseCase,
    private val getReservedCounselingListUseCase: GetReservedCounselingListUseCase,
) : BaseViewModel<Effect>(), CalendarScrollListener {
    val today: LocalDate = LocalDate.now()

    val daysOfWeek = daysOfWeek()

    val currentMonth: YearMonth = YearMonth.now()

    val startMonth: YearMonth = currentMonth.minusMonths(50)

    val endMonth: YearMonth = currentMonth.plusMonths(50)

    private val _oldDate = MutableLiveData<LocalDate>()
    val oldDate: LiveData<LocalDate>
        get() = _oldDate

    private val _selectedDate = MutableLiveData<LocalDate>()
    val selectedDate: LiveData<LocalDate>
        get() = _selectedDate

    private val _title = MutableLiveData<String>()
    val title: LiveData<String>
        get() = _title

    val scheduleList: LiveData<List<ReservationUiModel>> =
        getUserTypeUseCase().flatMapConcat { userType ->
            when (userType?.userType) {
                UserTypeOption.CLIENT -> {
                    getReservedCounselingListUseCase(userType.email)
                }
                UserTypeOption.COUNSELOR -> {
                    getReservedClientListUseCase(userType.email)
                }
                else -> {
                    flow { emit(emptyList()) }
                }
            }
        }.map {
            it.map {
                ReservationUiModel(it)
            }
        }
            .asLiveData()

    fun selectDate(date: LocalDate) {
        if (_selectedDate.value != date) {
            _oldDate.value = _selectedDate.value
            _selectedDate.value = date
        }
    }

    fun onClick(schedule: ReservationUiModel) {
//        TODO 화면 이동
    }

    override fun onScrolled(calendarMonth: CalendarMonth) {
        if (calendarMonth.yearMonth.year == today.year) {
            _title.value = DateTimeFormatter.ofPattern("MMMM").format(calendarMonth.yearMonth)
        } else {
            _title.value = DateTimeFormatter.ofPattern("MMM yyyy").format(calendarMonth.yearMonth)
        }
        // Select the first day of the visible month.
        selectDate(calendarMonth.yearMonth.atDay(1))
    }
}
