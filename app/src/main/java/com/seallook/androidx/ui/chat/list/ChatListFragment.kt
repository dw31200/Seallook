package com.seallook.androidx.ui.chat.list

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentChatListBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.GetChatList: 대화하고 있는 대화방 리스트 가져오기
    2.ListView: 가져온 대화방 리스트를 보여주기
    3.NavigateToDetail: 대화방 클릭시 해당 대화방의 Detail로 이동
 */
@AndroidEntryPoint
class ChatListFragment : BaseFragment<FragmentChatListBinding, ChatListViewModel>(
    FragmentChatListBinding::inflate,
) {
    override val viewModel: ChatListViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        Unit
    }
}
