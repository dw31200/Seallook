package com.seallook.androidx.ui.mypage

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.ActivityNavigator
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentMypageBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MypageFragment :
    BaseFragment<FragmentMypageBinding, MypageViewModel, MypageEffect>(
        FragmentMypageBinding::inflate,
    ),
    MypageNavigation {
    override val viewModel: MypageViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            navigation = this@MypageFragment
        }
    }

    override fun onEffectCollect(effect: MypageEffect) {
        when (effect) {
            MypageEffect.SuccessSignOut -> navigateToSplash()
        }
    }

    override fun navigateToUpdateCounselorBasicInfo() {
        val action = MypageFragmentDirections.actionMypageFragmentToUpdateCounselorBasicInfoFragment()
        navigate(action)
    }

    override fun navigateToReservedCounselingList() {
        val action = MypageFragmentDirections.actionMypageFragmentToReservedCounselingListFragment()
        navigate(action)
    }

    private fun navigateToSplash() {
        val extras = ActivityNavigator.Extras.Builder()
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .build()
        val action = MypageFragmentDirections.actionMypageFragmentToSplashNavigation()
        navigate(action, extras)
    }
}
