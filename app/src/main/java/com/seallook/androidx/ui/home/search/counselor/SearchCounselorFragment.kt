package com.seallook.androidx.ui.home.search.counselor

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentSearchCounselorBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect
import com.seallook.androidx.ui.home.search.counselor.adapter.SearchCounselorAdapter
import dagger.hilt.android.AndroidEntryPoint

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
