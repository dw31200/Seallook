package com.seallook.androidx.ui.mypage.counselor.info.update.office

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.base.Effect
import com.seallook.androidx.databinding.FragmentUpdateOfficeBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.model.OfficeInfoUiModel
import com.seallook.androidx.ui.mypage.counselor.info.update.office.adapter.UpdateOfficeAdapter
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.바뀐 모델에 맞춰 수정
 */
@AndroidEntryPoint
class UpdateOfficeFragment :
    BaseFragment<FragmentUpdateOfficeBinding, UpdateOfficeViewModel, Effect>(
        FragmentUpdateOfficeBinding::inflate,
    ),
    OfficeNavigation {
    override val viewModel: UpdateOfficeViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            navigation = this@UpdateOfficeFragment
            officeResultList.adapter = UpdateOfficeAdapter()
            officeSearchButton.setOnClickListener {
                viewModel.searchOnClick(officeNameTextField.editText?.text.toString().trim())
            }
        }
    }

    override fun onEffectCollect(effect: Effect) = Unit

    override fun navigateToMypage(info: OfficeInfoUiModel) {
        viewModel.setOfficeInfo(info)
        val action = UpdateOfficeFragmentDirections.actionUpdateOfficeFragmentToUpdateCounselorBasicInfoFragment()
        navigate(action)
    }
}
