package com.seallook.androidx.ui.mypage.counselor.info.update.office

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentUpdateOfficeBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.mypage.counselor.info.update.office.adapter.UpdateOfficeAdapter
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.바뀐 모델에 맞춰 수정
 */
@AndroidEntryPoint
class UpdateOfficeFragment :
    BaseFragment<FragmentUpdateOfficeBinding, UpdateOfficeViewModel, UpdateOfficeEffect>(
        FragmentUpdateOfficeBinding::inflate,
    ) {
    override val viewModel: UpdateOfficeViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            officeResultList.adapter = UpdateOfficeAdapter {
                viewModel.setOfficeInfo(it)
            }
            officeSearchButton.setOnClickListener {
                viewModel.searchOnClick(officeNameTextField.editText?.text.toString().trim())
            }
        }
    }

    override fun onEffectCollect(effect: UpdateOfficeEffect) {
        when (effect) {
            UpdateOfficeEffect.SuccessUpdateOfficeId -> navigateToMypage()
            else -> Unit
        }
    }

    private fun navigateToMypage() {
        val action = UpdateOfficeFragmentDirections.actionUpdateOfficeFragmentToUpdateCounselorBasicInfoFragment()
        navigate(action)
    }
}
