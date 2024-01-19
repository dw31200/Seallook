package com.seallook.androidx.ui.reserve.counseling.form

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentReserveFormBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    상담 예약 완료 후 작성
 */
@AndroidEntryPoint
class ReserveFormFragment : BaseFragment<FragmentReserveFormBinding, ReserveFormViewModel, Effect>(
    FragmentReserveFormBinding::inflate,
) {
    override val viewModel: ReserveFormViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() = Unit

    override fun onEffectCollect(effect: Effect) = Unit
}
