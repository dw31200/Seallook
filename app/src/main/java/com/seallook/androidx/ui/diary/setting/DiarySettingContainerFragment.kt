package com.seallook.androidx.ui.diary.setting

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentDiarySettingContainerBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.main.MainGraphViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiarySettingContainerFragment : BaseFragment<FragmentDiarySettingContainerBinding, MainGraphViewModel>(
    FragmentDiarySettingContainerBinding::inflate,
) {
    override val viewModel: MainGraphViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        Unit
    }
}
