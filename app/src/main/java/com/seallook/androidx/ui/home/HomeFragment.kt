package com.seallook.androidx.ui.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.databinding.FragmentHomeBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.GetAuthType: 로그인한 계정의 타입 가져오기
    2.GetOfficeList: 사용자 위치 기반 우리동네 상담소 리스트 가져오기
    2.InitView: 가져온 타입에 해당하는 화면 보여주기
    3.ListView: 가져온 우리동네 상담소 보여주기
    4.Navigation: 상담신청 > 찾기 화면, 상담신청내역 > ReservedCounselingList, 검색창 돋보기 > 찾기 화면,
        뒤로가기 > 앱종료, 상담기관 > 상담사 > ReserveCounseling,
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate,
) {
    override val viewModel: HomeViewModel by viewModels()
    override fun viewModelVariableId(): Int = BR.vm
    override fun onViewCreatedAfterBinding() {
        binding.counselorNameTextField.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchCounselorFragment)
        }
        binding.reserveCounselingButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchCounselorFragment)
        }
    }
}
