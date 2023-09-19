package com.seallook.androidx.ui.mypage.setting

import androidx.fragment.app.viewModels
import androidx.navigation.navOptions
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.databinding.FragmentSettingBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.ext.findRootNavController
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.ListView: 세팅 메뉴 보여주기
    2.Navigation: 알람 설정 > Notification, 1:1 문의하기 > ChatDetail, 약관 및 정책 > ServiceContract,
        로그아웃/회원탈퇴 > 안내 팝업 > SignIn
 */
@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>(
    FragmentSettingBinding::inflate,
) {
    override val viewModel: SettingViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        binding.signOutButton.setOnClickListener {
            viewModel.signOut()
            findRootNavController().navigate(
                R.id.splashFragment,
                null,
                navOptions {
                    popUpTo(R.id.splashFragment)
                },
            )
        }
    }
}
