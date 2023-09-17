package com.seallook.androidx.ui.calendar

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentCalendarBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

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
        Unit
    }
}
