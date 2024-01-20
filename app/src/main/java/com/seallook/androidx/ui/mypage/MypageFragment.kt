package com.seallook.androidx.ui.mypage

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentMypageBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect
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
                val action = MypageFragmentDirections.actionMypageFragmentToSettingFragment()
                navigate(action)
            }
            editCounselorInfo.setOnClickListener {
                val action = MypageFragmentDirections.actionMypageFragmentToUpdateCounselorBasicInfoFragment()
                navigate(action)
            }
        }
    }

    override fun onEffectCollect(effect: Effect) = Unit
}
