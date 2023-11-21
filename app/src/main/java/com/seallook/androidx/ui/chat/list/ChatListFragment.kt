package com.seallook.androidx.ui.chat.list

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.base.Effect
import com.seallook.androidx.databinding.FragmentChatListBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    상담 예약 완료 후 작성
 */
@AndroidEntryPoint
class ChatListFragment : BaseFragment<FragmentChatListBinding, ChatListViewModel, Effect>(
    FragmentChatListBinding::inflate,
) {
    override val viewModel: ChatListViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() = Unit

    override fun onEffectCollect(effect: Effect) = Unit
}
