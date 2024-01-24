package com.seallook.androidx.ui.reserved.client.list

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentReservedClientListBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.reserved.client.list.adapter.ReservedClientListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservedClientListFragment :
    BaseFragment<FragmentReservedClientListBinding, ReservedClientListViewModel, ReservedClientListEffect>(
        FragmentReservedClientListBinding::inflate,
    ) {
    override val viewModel: ReservedClientListViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            reservedClientList.adapter = ReservedClientListAdapter()
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
}
