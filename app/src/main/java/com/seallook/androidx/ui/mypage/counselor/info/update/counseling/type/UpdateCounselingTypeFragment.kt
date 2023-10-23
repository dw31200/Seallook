package com.seallook.androidx.ui.mypage.counselor.info.update.counseling.type

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentUpdateCounselingTypeBinding
import com.seallook.androidx.ui.base.BaseFragment
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
}
