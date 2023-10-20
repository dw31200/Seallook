package com.seallook.androidx.ui.mypage.counselor.info.update.counseling.type

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentUpdateCounselingTypeBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.model.CounselingTypeUiModel
import com.seallook.androidx.ui.mypage.counselor.info.update.counseling.type.adapter.CounselingTypeAdapter
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.SetView: 추가하기 클릭시 상담인원, 소요시간, 금액, 자장버튼(클릭시 '수정' 변경, '수정'클릭시 역방향 및 수정가능) 노출
    2.Navigation: 다음 > Preview
 */
@AndroidEntryPoint
class UpdateCounselingTypeFragment :
    BaseFragment<FragmentUpdateCounselingTypeBinding, UpdateCounselingTypeViewModel>(
        FragmentUpdateCounselingTypeBinding::inflate,
    ),
    DeleteCounselingType {
    override val viewModel: UpdateCounselingTypeViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            counselingTypeList.adapter = CounselingTypeAdapter()
            updateTypeButton.setOnClickListener {
                updateCounselingType()
            }
            nextButton.setOnClickListener {
                findNavController().popBackStack()
            }
            delete = this@UpdateCounselingTypeFragment
        }
    }

    private fun updateCounselingType() {
        with(binding) {
            var id: Int? = null
            viewModel.counselingType.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    id = 0
                } else {
                    id = it[it.size - 1].id + 1
                }
            }
            val title = typeNameTextField.editText?.text.toString()
            val count = typeNumberField.editText?.text.toString().toInt()
            val time = typeTimeField.editText?.text.toString().toInt()
            val pay = typePayField.editText?.text.toString().toInt()
            viewModel.setCounselingType(
                CounselingTypeUiModel(
                    id ?: 0,
                    title,
                    count,
                    time,
                    pay,
                ),
            )
        }
    }

    override fun deleteCounselingType(counselingTypeId: Int) {
        viewModel.deleteCounselingType(counselingTypeId)
    }
}
