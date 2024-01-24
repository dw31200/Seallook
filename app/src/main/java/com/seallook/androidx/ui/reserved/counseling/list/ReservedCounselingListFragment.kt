package com.seallook.androidx.ui.reserved.counseling.list

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentReservedCounselingListBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect
import com.seallook.androidx.ui.reserved.counseling.list.adapter.ReservedCounselingListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservedCounselingListFragment :
    BaseFragment<FragmentReservedCounselingListBinding, ReservedCounselingListViewModel, Effect>(
        FragmentReservedCounselingListBinding::inflate,
    ) {
    override val viewModel: ReservedCounselingListViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            reservedCounselingList.adapter = ReservedCounselingListAdapter {
                navigateToDetail(it)
            }
        }
    }

    override fun onEffectCollect(effect: Effect) = Unit

    private fun navigateToDetail(reservationId: String) {
        val action = ReservedCounselingListFragmentDirections
            .actionReservedCounselingListFragmentToReservedCounselingDetailFragment(reservationId)
        navigate(action)
    }
}
