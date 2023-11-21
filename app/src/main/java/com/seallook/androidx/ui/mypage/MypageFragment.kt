package com.seallook.androidx.ui.mypage

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.base.Effect
import com.seallook.androidx.databinding.FragmentMypageBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    상담 예약 완료 후 작성
 */
@AndroidEntryPoint
class MypageFragment : BaseFragment<FragmentMypageBinding, MypageViewModel, Effect>(
    FragmentMypageBinding::inflate,
) {
    override val viewModel: MypageViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            settingButton.setOnClickListener {
                findNavController().navigate(R.id.action_mypageFragment_to_settingFragment)
            }
            editCounselorInfo.setOnClickListener {
                findNavController().navigate(R.id.action_mypageFragment_to_updateCounselorBasicInfoFragment)
            }
        }
    }

    override fun onEffectCollect(effect: Effect) = Unit
}
