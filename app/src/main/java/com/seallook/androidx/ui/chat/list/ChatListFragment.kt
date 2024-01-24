package com.seallook.androidx.ui.chat.list

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentChatListBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatListFragment : BaseFragment<FragmentChatListBinding, ChatListViewModel, Effect>(
    FragmentChatListBinding::inflate,
) {
    override val viewModel: ChatListViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() = Unit

    override fun onEffectCollect(effect: Effect) = Unit
}
