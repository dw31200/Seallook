package com.seallook.androidx.ui.reserved.counseling.list

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentReservedCounselingListBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.reserved.counseling.list.adapter.ReservedCounselingListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservedCounselingListFragment :
    BaseFragment<FragmentReservedCounselingListBinding, ReservedCounselingListViewModel, ReservedClientListEffect>(
        FragmentReservedCounselingListBinding::inflate,
    ) {
    override val viewModel: ReservedCounselingListViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            reservedCounselingList.adapter = ReservedCounselingListAdapter {
                navigateToDetail(it)
            }
            updateConfirm = viewModel
        }
    }

    override fun onEffectCollect(effect: ReservedClientListEffect) {
        when (effect) {
            ReservedClientListEffect.FailureUpdateConfirm -> {
                showFailMessage("업데이트에 실패했습니다.")
            }
            ReservedClientListEffect.SuccessUpdateConfirm -> {
                dismissProgressDialog()
            }
            ReservedClientListEffect.UpdateConfirm -> {
                showProgressDialog("업데이트 중입니다.")
            }
        }
    }

    private fun navigateToDetail(reservationId: String) {
        val action = ReservedCounselingListFragmentDirections
            .actionReservedCounselingListFragmentToReservedCounselingDetailFragment(reservationId)
        navigate(action)
    }
}
