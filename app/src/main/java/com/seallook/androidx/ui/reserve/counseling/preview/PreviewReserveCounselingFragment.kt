package com.seallook.androidx.ui.reserve.counseling.preview

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentPreviewReserveCounselingBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.GetReserveInfo: 예약하려는 상담 정보 가져오기
    2.ListView: 가져온 상담 정보 보여주기
    3.Navigation: 다음 > PaymentCounseling, 뒤로가기 > ReserveCounselingContract
    4.DeleteReserveData: 앱 종료시 저장되어 있는 모든 예약 서류 삭제하기
 */
@AndroidEntryPoint
class PreviewReserveCounselingFragment : BaseFragment<FragmentPreviewReserveCounselingBinding, PreviewReserveCounselingViewModel>(
    FragmentPreviewReserveCounselingBinding::inflate,
) {
    override val viewModel: PreviewReserveCounselingViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        Unit
    }
}
