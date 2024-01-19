package com.seallook.androidx.ui.home.search.counselor

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentSearchCounselorBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect
import com.seallook.androidx.ui.home.search.counselor.adapter.SearchCounselorAdapter
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.DAO에 이름으로 정렬하는 메서드 구현
    2.검색 버튼 클릭시 해당 검색어 기준 정렬 리스트 가져오기
    3.정렬된 리스트로 보여주기
 */
@AndroidEntryPoint
class SearchCounselorFragment :
    BaseFragment<FragmentSearchCounselorBinding, SearchCounselorViewModel, Effect>(
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

    override fun onEffectCollect(effect: Effect) = Unit

    override fun navigateToReserveCounseling(email: String) {
        val action = SearchCounselorFragmentDirections.actionSearchCounselorFragmentToReserveCounselingFragment(email)
        navigate(action)
    }
}
