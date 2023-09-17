package com.seallook.androidx.ui.mypage

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentMypageBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.GetAuthType: 계정 타입 가져오기
    2.ListView: 계정 타입에 따라 화면 보여주기,[내담자(프로필 관리 버튼, 상담신청내역 버튼, 리뷰관리 버튼, 환경설정 버튼),
        상담자(프로필 관리 버튼, 상담사 설정 버튼, 예약된 상담 리스트 및 더보기 버튼, 환경설정 버튼),
        기관(프로필 관리 버튼, 매출현황 버튼, 정산신청 버튼, 정산내역 버튼, 예약된 상담 리스트 및 더보기 버튼, 환경설정 버튼)]
    3.Navigation: 프로필 관리 > Myprofile, 상담신청내역 > ReservedCounselingList, 리뷰관리 > ReviewList, 환경설정 > Setting,
        상담사 설정 > UpdateCounselerBasicInfo, 더보기 > ReservedClientList, 매출현황 > SalesList, 정산신청 > CurrentRequestPay,
        정산내역 > RequestedPayList, 예약된 상담 리스트 중 아이템 > ReservedClientDetail
 */
@AndroidEntryPoint
class MypageFragment : BaseFragment<FragmentMypageBinding, MypageViewModel>(
    FragmentMypageBinding::inflate,
) {
    override val viewModel: MypageViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        Unit
    }
}
