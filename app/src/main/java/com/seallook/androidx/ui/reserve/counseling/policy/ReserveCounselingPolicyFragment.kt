package com.seallook.androidx.ui.reserve.counseling.policy

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.base.Effect
import com.seallook.androidx.databinding.FragmentReserveCounselingPolicyBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    상담 예약 완료 후 작성
 */
@AndroidEntryPoint
class ReserveCounselingPolicyFragment : BaseFragment<FragmentReserveCounselingPolicyBinding, ReserveCounselingPolicyViewModel, Effect>(
    FragmentReserveCounselingPolicyBinding::inflate,
) {
    override val viewModel: ReserveCounselingPolicyViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() = Unit

    override fun onEffectCollect(effect: Effect) = Unit
}
