package com.seallook.androidx.ui.reserve.counseling

import androidx.fragment.app.viewModels
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentReserveCounselingBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.reserve.counseling.adapter.ReserveCounselingAdapter
import com.seallook.androidx.ui.reserve.counseling.calendar.ReserveCounselingCalendarBinder
import com.seallook.androidx.ui.utils.getWeekPageTitle
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.YearMonth

@AndroidEntryPoint
class ReserveCounselingFragment :
    BaseFragment<FragmentReserveCounselingBinding, ReserveCounselingViewModel, ReserveCounselingEffect>(
        FragmentReserveCounselingBinding::inflate,
    ) {
    override val viewModel: ReserveCounselingViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            dateSelectorList.adapter = ReserveCounselingAdapter()
            weekCalendarView.dayBinder = ReserveCounselingCalendarBinder()

            weekCalendarView.weekScrollListener = { weekDays ->
                counselingDateMonthText.text = getWeekPageTitle(weekDays)
            }
            val currentMonth = YearMonth.now()
            weekCalendarView.setup(
                currentMonth.minusMonths(5).atStartOfMonth(),
                currentMonth.plusMonths(5).atEndOfMonth(),
                firstDayOfWeekFromLocale(),
            )
            weekCalendarView.scrollToDate(LocalDate.now())
        }
    }

    override fun onEffectCollect(effect: ReserveCounselingEffect) {
        when (effect) {
            ReserveCounselingEffect.NavigateToHome -> {
                val action = ReserveCounselingFragmentDirections.actionReserveCounselingFragmentToHomeFragment()
                navigate(action)
            }

            ReserveCounselingEffect.AlreadyReserve -> {
                showFailMessage("이미 예약하신 일정입니다.")
            }
        }
    }
}
