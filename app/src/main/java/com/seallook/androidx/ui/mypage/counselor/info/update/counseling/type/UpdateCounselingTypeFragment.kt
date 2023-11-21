package com.seallook.androidx.ui.mypage.counselor.info.update.counseling.type

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.seallook.androidx.BR
import com.seallook.androidx.base.Effect
import com.seallook.androidx.databinding.FragmentUpdateCounselingTypeBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.mypage.counselor.info.update.counseling.type.adapter.CounselingTypeAdapter
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.바뀐 모델에 맞춰서 수정
 */
@AndroidEntryPoint
class UpdateCounselingTypeFragment :
    BaseFragment<FragmentUpdateCounselingTypeBinding, UpdateCounselingTypeViewModel, Effect>(
        FragmentUpdateCounselingTypeBinding::inflate,
    ) {
    override val viewModel: UpdateCounselingTypeViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            counselingTypeList.adapter = CounselingTypeAdapter()
            nextButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onEffectCollect(effect: Effect) = Unit
}
