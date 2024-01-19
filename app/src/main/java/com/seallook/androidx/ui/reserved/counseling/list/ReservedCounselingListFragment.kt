package com.seallook.androidx.ui.reserved.counseling.list

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentReservedCounselingListBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect
import com.seallook.androidx.ui.reserved.counseling.list.adapter.ReservedCounselingListAdapter
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.로그인된 사용자 email로 reservation 데이터 가져오기
    2.가져온 reservation 보여주기
    3.Navigation: 상담 아이템 > ReservedCounselingDetail, 뒤로가기 > 이전화면
 */
@AndroidEntryPoint
class ReservedCounselingListFragment :
    BaseFragment<FragmentReservedCounselingListBinding, ReservedCounselingListViewModel, Effect>(
        FragmentReservedCounselingListBinding::inflate,
    ) {
    override val viewModel: ReservedCounselingListViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            reservedCounselingList.adapter = ReservedCounselingListAdapter {
                navigateToDetail(it)
            }
        }
    }

    override fun onEffectCollect(effect: Effect) = Unit

    private fun navigateToDetail(reservationId: String) {
        val action = ReservedCounselingListFragmentDirections
            .actionReservedCounselingListFragmentToReservedCounselingDetailFragment(reservationId)
        navigate(action)
    }
}
