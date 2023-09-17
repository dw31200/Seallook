package com.seallook.androidx.ui.mypage

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentMypageContainerBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.main.MainGraphViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MypageContainerFragment : BaseFragment<FragmentMypageContainerBinding, MainGraphViewModel>(
    FragmentMypageContainerBinding::inflate,
) {
    override val viewModel: MainGraphViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        Unit
    }
}
