package com.seallook.androidx.ui.reserve.counseling

import androidx.fragment.app.viewModels
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentReserveCounselingBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.reserve.counseling.calendar.ReserveCounselingCalendarBinder
import com.seallook.androidx.ui.utils.getWeekPageTitle
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.YearMonth

/* TODO
    1.GetCounselingInfo: 예약하려는 상담 정보 가져오기
    2.ListView: 가져온 상담 정보 보여주기
    3.Navigation: 후기 더보기 > ReviewList, 신청하기 > 안내 팝업 > ReserveForm
    4.SaveReserveSchedule: 신청하기 클릭시 예약하려는 일정 저장하기
    5.DeleteReserveSchedule: 뒤로가기 혹은 앱 종료시 예약하려는 일정 삭제하기
 */
@AndroidEntryPoint
class ReserveCounselingFragment :
    BaseFragment<FragmentReserveCounselingBinding, ReserveCounselingViewModel>(
        FragmentReserveCounselingBinding::inflate,
    ) {
    override val viewModel: ReserveCounselingViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        binding.weekCalendarView.dayBinder = ReserveCounselingCalendarBinder()

        binding.weekCalendarView.weekScrollListener = { weekDays ->
            binding.counselingDateMonthText.text = getWeekPageTitle(weekDays)
        }
        val currentMonth = YearMonth.now()
        binding.weekCalendarView.setup(
            currentMonth.minusMonths(5).atStartOfMonth(),
            currentMonth.plusMonths(5).atEndOfMonth(),
            firstDayOfWeekFromLocale(),
        )
        binding.weekCalendarView.scrollToDate(LocalDate.now())
    }
}
