package com.seallook.androidx.ui.home

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.base.Effect
import com.seallook.androidx.databinding.FragmentHomeBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.home.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.서버에서 가져온 CounselorList DB에 저장 및 flow로 가져오기
    2.서버에서 가져온 계정 타입 DB에 저장 및 flow로 가져오기
    필요한 모델: CounselorInfo(id 자동생성으로 변경, documentId 추가), OfficeInfo(id 자동생성으로 변경, documentId 추가)
 */
@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel, Effect>(
        FragmentHomeBinding::inflate,
    ),
    HomeNavigation {
    override val viewModel: HomeViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            counselorList.adapter = HomeAdapter()
            navigation = this@HomeFragment
            counselorNameTextField.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToSearchCounselorFragment()
                navigate(action)
            }
            reserveCounselingButton.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToSearchCounselorFragment()
                navigate(action)
            }
            reservedClientListButton.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToReservedClientListFragment()
                navigate(action)
            }
        }
    }

    override fun onEffectCollect(effect: Effect) = Unit
    override fun navigateToReserveCounseling(email: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToReserveCounselingFragment(email)
        navigate(action)
    }
}
