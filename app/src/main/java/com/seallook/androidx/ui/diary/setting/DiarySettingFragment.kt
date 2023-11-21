package com.seallook.androidx.ui.diary.setting

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.base.Effect
import com.seallook.androidx.databinding.FragmentDiarySettingBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    상담 예약 완료 후 작성
 */
@AndroidEntryPoint
class DiarySettingFragment : BaseFragment<FragmentDiarySettingBinding, DiarySettingViewModel, Effect>(
    FragmentDiarySettingBinding::inflate,
) {
    override val viewModel: DiarySettingViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() = Unit

    override fun onEffectCollect(effect: Effect) = Unit
}
