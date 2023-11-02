package com.seallook.androidx.ui.home.search.counseler

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentSearchCounselorBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.home.search.counseler.adapter.SearchCounselorAdapter
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.GetCounselerList: 검색창의 텍스트가 이름에 들어간 기관 혹은 상담사 리스트 가져오기
    2.ListView: 가져온 상담사 리스트 보여주기. 보여준 뒤에 리스트 중에 기관 클릭시 해당 기관소속 상담사 리스트로 보여주기
    3.Navigation: 상담사 클릭시 해당 상담사 디테일 화면 이동
 */
@AndroidEntryPoint
class SearchCounselorFragment :
    BaseFragment<FragmentSearchCounselorBinding, SearchCounselorViewModel>(
        FragmentSearchCounselorBinding::inflate,
    ),
    SearchCounselorNavigation {
    override val viewModel: SearchCounselorViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            counselorList.adapter = SearchCounselorAdapter()
            navigation = this@SearchCounselorFragment
        }
    }

    override fun navigateToReserveCounseling(email: String) {
        val action = SearchCounselorFragmentDirections.actionSearchCounselorFragmentToReserveCounselingFragment(email)
        findNavController().navigate(action)
    }
}
