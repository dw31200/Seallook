package com.seallook.androidx.ui.reserve.counseling.policy

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentReserveCounselingPolicyBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.GetCounselingContractList: 상담 예약에 필요한 계약서 리스트 가져오기
    2.ListView: 가져온 계역서 리스트 보여주기, 각 계약서 동의 체크 완료시에만 다음 버튼 동적 노출
    3.SaveContract: 다음 클릭시 계약서 저장하기
    4.Navigation: 다음 > PreviewReserveCounseling, 뒤로가기 > ReserveForm
    5.DeleteContract: 뒤로가기 혹은 앱 종료시 계약서 삭제하기
 */
@AndroidEntryPoint
class ReserveCounselingPolicyFragment : BaseFragment<FragmentReserveCounselingPolicyBinding, ReserveCounselingPolicyViewModel>(
    FragmentReserveCounselingPolicyBinding::inflate,
) {
    override val viewModel: ReserveCounselingPolicyViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        Unit
    }
}
