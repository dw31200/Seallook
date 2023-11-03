package com.seallook.androidx.ui.reserve.counseling

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.view.ViewContainer
import com.kizitonwose.calendar.view.WeekDayBinder
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.databinding.FragmentReserveCounselingBinding
import com.seallook.androidx.databinding.ReserveCounselingCalendarDayBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.calendar.widget.getColorCompat
import com.seallook.androidx.ui.utils.displayText
import com.seallook.androidx.ui.utils.getWeekPageTitle
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

/* TODO
    1.GetCounselingInfo: 예약하려는 상담 정보 가져오기
    2.ListView: 가져온 상담 정보 보여주기
    3.Navigation: 후기 더보기 > ReviewList, 신청하기 > 안내 팝업 > ReserveForm
    4.SaveReserveSchedule: 신청하기 클릭시 예약하려는 일정 저장하기
    5.DeleteReserveSchedule: 뒤로가기 혹은 앱 종료시 예약하려는 일정 삭제하기
 */
@AndroidEntryPoint
class ReserveCounselingFragment : BaseFragment<FragmentReserveCounselingBinding, ReserveCounselingViewModel>(
    FragmentReserveCounselingBinding::inflate,
) {
    override val viewModel: ReserveCounselingViewModel by viewModels()
    private var selectedDate = LocalDate.now()
    private val dateFormatter = DateTimeFormatter.ofPattern("dd")

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        Unit
        class DayViewContainer(view: View) : ViewContainer(view) {
            val bind = ReserveCounselingCalendarDayBinding.bind(view)
            lateinit var day: WeekDay

            init {
                view.setOnClickListener {
                    if (selectedDate != day.date) {
                        val oldDate = selectedDate
                        selectedDate = day.date
                        binding.weekCalendarView.notifyDateChanged(day.date)
                        oldDate?.let { binding.weekCalendarView.notifyDateChanged(it) }
                    }
                }
            }

            fun bind(day: WeekDay) {
                this.day = day
                bind.dateTextView.text = dateFormatter.format(day.date)
                bind.dayTextView.text = day.date.dayOfWeek.displayText()
                val colorRes = if (day.date == selectedDate) {
                    R.color.colorPrimary
                } else {
                    R.color.black
                }
                bind.dateTextView.setTextColor(view.context.getColorCompat(colorRes))
                bind.selectedView.isVisible = day.date == selectedDate
            }
        }

        binding.weekCalendarView.dayBinder = object : WeekDayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, data: WeekDay) = container.bind(data)
        }

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
