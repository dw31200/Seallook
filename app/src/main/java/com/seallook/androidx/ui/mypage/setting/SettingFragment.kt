package com.seallook.androidx.ui.mypage.setting

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.ActivityNavigator
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentSettingBinding
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    상담 예약 완료 후 작성
 */
@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel, Effect>(
    FragmentSettingBinding::inflate,
) {
    override val viewModel: SettingViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    private val extras = ActivityNavigator.Extras.Builder()
        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        .build()

    override fun onViewCreatedAfterBinding() {
        binding.signOutButton.setOnClickListener {
            viewModel.signOut()
            val action = SettingFragmentDirections.actionSettingFragmentToSplashNavigation()
            navigate(action, extras)
        }
    }

    override fun onEffectCollect(effect: Effect) = Unit
}
