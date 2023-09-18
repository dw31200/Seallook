package com.seallook.androidx.ui.reserve.counseling.form

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentReserveFormBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.GetCounselingForm: 상담 신청서 가져오기
    2.ListView: 가져온 상담 신청서 보여주기
    3.SaveForm: 다음 클릭시 신청서 내용 저장하기
    4.Navigation: 다음 > ReserveCounselingContract, 뒤로가기 > ReserveCounseling
    5.DeleteForm: 뒤로가기 혹은 앱 종료시 신청서 내용 삭제하기
 */
@AndroidEntryPoint
class ReserveFormFragment : BaseFragment<FragmentReserveFormBinding, ReserveFormViewModel>(
    FragmentReserveFormBinding::inflate,
) {
    override val viewModel: ReserveFormViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        Unit
    }
}
