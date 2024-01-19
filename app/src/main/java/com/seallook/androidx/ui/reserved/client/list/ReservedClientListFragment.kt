package com.seallook.androidx.ui.reserved.client.list

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentReservedClientListBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect
import com.seallook.androidx.ui.reserved.client.list.adapter.ReservedClientListAdapter
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.reservation(documentId, clientEmail, counselorEmail, scheduleId, approve) 중에 로그인한 상담사 email에 해당하는
     데이터 모두 가져오기
    2.가져온 reservation 데이터 보여주기
    3.Navigation: 내담자 아이템 > ReservedClientDetail, 뒤로가기 > 이전화면
 */
@AndroidEntryPoint
class ReservedClientListFragment :
    BaseFragment<FragmentReservedClientListBinding, ReservedClientListViewModel, Effect>(
        FragmentReservedClientListBinding::inflate,
    ) {
    override val viewModel: ReservedClientListViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            reservedClientList.adapter = ReservedClientListAdapter()
        }
    }

    override fun onEffectCollect(effect: Effect) = Unit
}
