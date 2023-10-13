package com.seallook.androidx.ui.calendar

import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import com.kizitonwose.calendar.view.ViewContainer
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.databinding.CalendarDayLayoutBinding
import com.seallook.androidx.databinding.CalendarHeaderLayoutBinding
import com.seallook.androidx.databinding.FragmentCalendarBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.calendar.widget.Schedule
import com.seallook.androidx.ui.calendar.widget.ScheduleAdapter
import com.seallook.androidx.ui.calendar.widget.addStatusBarColorUpdate
import com.seallook.androidx.ui.calendar.widget.dpToPx
import com.seallook.androidx.ui.calendar.widget.getColorCompat
import com.seallook.androidx.ui.calendar.widget.inputMethodManager
import com.seallook.androidx.ui.calendar.widget.makeInVisible
import com.seallook.androidx.ui.calendar.widget.makeVisible
import com.seallook.androidx.ui.calendar.widget.setTextColorRes
import dagger.hilt.android.AndroidEntryPoint
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.UUID

/* TODO
    1.GetReserved: 로그인 계정의 예약 현황을 가져오기
    2.InitView: 가져온 일정 달력에 보여주기
    3.FloatReserved: 특정 날짜를 클릭시 해당 날짜의 플로팅뷰 보여주기
    4.ListView: 클릭된 날짜의 디테일을 리사이클러뷰로 플로팅뷰에 보여주기
    5.NavigateToReservedDetail: 플로팅뷰에서 특정 일정을 클릭시 해당 일정의 ReservedDetail로 이동
    6.NavigateToSettingSchedule: 기본 화면에서 +버튼 클릭시 SettingSchedule로 이동
 */

@AndroidEntryPoint
class CalendarFragment : BaseFragment<FragmentCalendarBinding, CalendarViewModel>(
    FragmentCalendarBinding::inflate,
) {
    override val viewModel: CalendarViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        addStatusBarColorUpdate(R.color.statusbar_color)
        binding.exThreeRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = scheduleAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }

        binding.exThreeCalendar.monthScrollListener = {
            binding.toolbar.title = if (it.yearMonth.year == today.year) {
                titleSameYearFormatter.format(it.yearMonth)
            } else {
                titleFormatter.format(it.yearMonth)
            }
            // Select the first day of the visible month.
            selectDate(it.yearMonth.atDay(1))
        }
        val daysOfWeek = daysOfWeek()
        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(50)
        val endMonth = currentMonth.plusMonths(50)
        configureBinders(daysOfWeek)
        binding.exThreeCalendar.apply {
            setup(startMonth, endMonth, daysOfWeek.first())
            scrollToMonth(currentMonth)
        }
//        if (savedInstanceState == null) {
        // Show today's events initially.
//            binding.exThreeCalendar.post { selectDate(today) }
//        }
        binding.exThreeAddButton.setOnClickListener { inputDialog.show() }
    }

    private val scheduleAdapter = ScheduleAdapter {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.dialog_delete_confirmation)
            .setPositiveButton(R.string.delete) { _, _ ->
                deleteSchedule(it)
            }
            .setNegativeButton(R.string.close, null)
            .show()
    }
    private val inputDialog by lazy {
        val editText = AppCompatEditText(requireContext())
        val layout = FrameLayout(requireContext()).apply {
            // Setting the padding on the EditText only pads the input area
            // not the entire EditText so we wrap it in a FrameLayout.
            val padding = dpToPx(20, requireContext())
            setPadding(padding, padding, padding, padding)
            addView(editText, FrameLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT))
        }
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.input_dialog_title))
            .setView(layout)
            .setPositiveButton(R.string.save) { _, _ ->
                saveSchedule(editText.text.toString())
                // Prepare EditText for reuse.
                editText.setText("")
            }
            .setNegativeButton(R.string.close, null)
            .create()
            .apply {
                setOnShowListener {
                    // Show the keyboard
                    editText.requestFocus()
                    context.inputMethodManager
                        .toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
                }
                setOnDismissListener {
                    // Hide the keyboard
                    context.inputMethodManager
                        .toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                }
            }
    }
    private var selectedDate: LocalDate? = null
    private val today = LocalDate.now()
    private val titleSameYearFormatter = DateTimeFormatter.ofPattern("MMMM")
    private val titleFormatter = DateTimeFormatter.ofPattern("MMM yyyy")
    private val selectionFormatter = DateTimeFormatter.ofPattern("d MMM yyyy")
    private val scheduleList = mutableMapOf<LocalDate, List<Schedule>>()

    private fun selectDate(date: LocalDate) {
        if (selectedDate != date) {
            val oldDate = selectedDate
            selectedDate = date
            oldDate?.let { binding.exThreeCalendar.notifyDateChanged(it) }
            binding.exThreeCalendar.notifyDateChanged(date)
            updateAdapterForDate(date)
        }
    }

    private fun saveSchedule(text: String) {
        if (text.isBlank()) {
            Toast.makeText(requireContext(), R.string.empty_input_text, Toast.LENGTH_LONG)
                .show()
        } else {
            selectedDate?.let {
                scheduleList[it] =
                    scheduleList[it].orEmpty().plus(Schedule(UUID.randomUUID().toString(), text, it))
                updateAdapterForDate(it)
            }
        }
    }

    private fun deleteSchedule(schedule: Schedule) {
        val date = schedule.date
        scheduleList[date] = scheduleList[date].orEmpty().minus(schedule)
        updateAdapterForDate(date)
    }

    private fun updateAdapterForDate(date: LocalDate) {
        scheduleAdapter.apply {
            scheduleList.clear()
            scheduleList.addAll(this@CalendarFragment.scheduleList[date].orEmpty())
            notifyDataSetChanged()
        }
        binding.exThreeSelectedDateText.text = selectionFormatter.format(date)
    }

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

    private fun configureBinders(daysOfWeek: List<DayOfWeek>) {
        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay // Will be set when this container is bound.
            val binding = CalendarDayLayoutBinding.bind(view)

            init {
                view.setOnClickListener {
                    if (day.position == DayPosition.MonthDate) {
                        selectDate(day.date)
                    }
                }
            }
        }
        binding.exThreeCalendar.dayBinder = object : MonthDayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.day = data
                val textView = container.binding.exThreeDayText
                val dotView = container.binding.exThreeDotView

                textView.text = data.date.dayOfMonth.toString()

                if (data.position == DayPosition.MonthDate) {
                    textView.makeVisible()
                    when (data.date) {
                        today -> {
                            textView.setTextColorRes(R.color.white)
                            textView.setBackgroundResource(R.drawable.today_bg)
                            dotView.makeInVisible()
                        }

                        selectedDate -> {
                            textView.setTextColorRes(R.color.blue)
                            textView.setBackgroundResource(R.drawable.selected_bg)
                            dotView.makeInVisible()
                        }

                        else -> {
                            textView.setTextColorRes(R.color.black)
                            textView.background = null
                            dotView.isVisible = scheduleList[data.date].orEmpty().isNotEmpty()
                        }
                    }
                } else {
                    textView.makeInVisible()
                    dotView.makeInVisible()
                }
            }
        }

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val legendLayout = CalendarHeaderLayoutBinding.bind(view).legendLayout.root
        }
        binding.exThreeCalendar.monthHeaderBinder =
            object : MonthHeaderFooterBinder<MonthViewContainer> {
                override fun create(view: View) = MonthViewContainer(view)
                override fun bind(container: MonthViewContainer, data: CalendarMonth) {
                    // Setup each header day text if we have not done that already.
                    if (container.legendLayout.tag == null) {
                        container.legendLayout.tag = data.yearMonth
                        container.legendLayout.children.map { it as TextView }
                            .forEachIndexed { index, tv ->
                                tv.text = daysOfWeek[index].name.first().toString()
                                tv.setTextColorRes(R.color.black)
                            }
                    }
                }
            }
    }
}
