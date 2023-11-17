package com.seallook.androidx.ui.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.databinding.FragmentHomeBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.home.adapter.HomeAdapter
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.서버에서 가져온 CounselorList DB에 저장 및 flow로 가져오기
    2.서버에서 가져온 계정 타입 DB에 저장 및 flow로 가져오기
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate,
) {
    override val viewModel: HomeViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            counselorList.adapter = HomeAdapter()
            counselorNameTextField.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_searchCounselorFragment)
            }
            reserveCounselingButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_searchCounselorFragment)
            }
        }
    }
}
