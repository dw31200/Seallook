package com.seallook.androidx.ui.calendar

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.databinding.FragmentCalendarBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect
import com.seallook.androidx.ui.calendar.adapter.ScheduleAdapter
import com.seallook.androidx.ui.calendar.binder.CalendarDayBinder
import com.seallook.androidx.ui.calendar.binder.CalendarHeaderBinder
import com.seallook.androidx.ui.calendar.widget.addStatusBarColorUpdate
import com.seallook.androidx.ui.calendar.widget.getColorCompat
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    상담 예약 완료 후 작성
 */

@AndroidEntryPoint
class CalendarFragment : BaseFragment<FragmentCalendarBinding, CalendarViewModel, Effect>(
    FragmentCalendarBinding::inflate,
) {
    override val viewModel: CalendarViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        addStatusBarColorUpdate(R.color.statusbar_color)
        with(binding) {
            scheduleList.apply {
                adapter = ScheduleAdapter {
                    viewModel.onClick(it)
                }
                addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            }
            calendarView.dayBinder = CalendarDayBinder {
                viewModel.selectDate(it)
            }
            calendarView.monthHeaderBinder = CalendarHeaderBinder()
            scrollListener = viewModel
        }
    }

    override fun onEffectCollect(effect: Effect) = Unit

    override fun onStart() {
        super.onStart()
        binding.toolbar.setBackgroundColor(
            requireContext().getColorCompat(R.color.toolbar_color),
        )
    }

    override fun onStop() {
        super.onStop()
        binding.toolbar.setBackgroundColor(
            requireContext().getColorCompat(R.color.colorPrimary),
        )
    }
}
