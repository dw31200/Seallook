package com.seallook.androidx.ui.reserve.counseling.policy

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentReserveCounselingPolicyBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReserveCounselingPolicyFragment : BaseFragment<FragmentReserveCounselingPolicyBinding, ReserveCounselingPolicyViewModel, Effect>(
    FragmentReserveCounselingPolicyBinding::inflate,
) {
    override val viewModel: ReserveCounselingPolicyViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() = Unit

    override fun onEffectCollect(effect: Effect) = Unit
}
