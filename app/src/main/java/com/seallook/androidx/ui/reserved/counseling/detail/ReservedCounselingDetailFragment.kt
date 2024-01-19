package com.seallook.androidx.ui.reserved.counseling.detail

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentReservedCounselingDetailBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.선택한 counselorEmail, scheduleId로 CounselingSchedule, CounselingType 데이터 가져오기
    2.가져온 정보 보여주기
    3.일정 취소 클릭시 counselingSchedule의 reservation false로 업데이트
 */
@AndroidEntryPoint
class ReservedCounselingDetailFragment :
    BaseFragment<FragmentReservedCounselingDetailBinding, ReservedCounselingDetailViewModel, Effect>(
        FragmentReservedCounselingDetailBinding::inflate,
    ) {
    override val viewModel: ReservedCounselingDetailViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() = Unit
    override fun onEffectCollect(effect: Effect) = Unit
}
