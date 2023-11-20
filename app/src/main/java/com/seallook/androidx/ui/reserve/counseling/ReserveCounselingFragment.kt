package com.seallook.androidx.ui.reserve.counseling

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.seallook.androidx.BR
import com.seallook.androidx.base.observeEvent
import com.seallook.androidx.databinding.FragmentReserveCounselingBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.reserve.counseling.adapter.ReserveCounselingAdapter
import com.seallook.androidx.ui.reserve.counseling.calendar.ReserveCounselingCalendarBinder
import com.seallook.androidx.ui.utils.getWeekPageTitle
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.YearMonth

/* TODO
    1.상담사 스케쥴, 타입, 정보 자동생성ID로 변경
    2.상담사 스케쥴 DB 저장 및 flow로 가져오기
    3.상담 예약 신청시 transaction으로 해당 일정 예약 여부 확인 후 reservation 필드 true로 업데이트
    4.reservation 필드 업데이트 성공시 Reservation 컬렉션에 예약 정보 업로드
    필요한 모델: CounselingSchedule, CounselingType, Reservation(documentId, clientEmail, counselorEmail, scheduleId, approve, 개별 컬렉션으로 변경),
 */
@AndroidEntryPoint
class ReserveCounselingFragment :
    BaseFragment<FragmentReserveCounselingBinding, ReserveCounselingViewModel>(
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
        viewModel.effect.observeEvent(viewLifecycleOwner) {
            when (it) {
                ReserveCounselingEffect.NavigateToHome -> {
                    findNavController().navigate(ReserveCounselingFragmentDirections.actionReserveCounselingFragmentToHomeFragment())
                }
            }
        }
    }
}
