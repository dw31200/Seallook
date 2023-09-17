package com.seallook.androidx.ui.chat

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentChatContainerBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.main.MainGraphViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatContainerFragment : BaseFragment<FragmentChatContainerBinding, MainGraphViewModel>(
    FragmentChatContainerBinding::inflate,
) {
    override val viewModel: MainGraphViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        Unit
    }
}
