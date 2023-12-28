package com.seallook.androidx.ui.splash

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.ActivityNavigator
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentSplashBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel, SplashEffect>(
    FragmentSplashBinding::inflate,
) {
    override val viewModel: SplashViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() = Unit

    override fun onEffectCollect(effect: SplashEffect) {
        val extras = ActivityNavigator.Extras.Builder()
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .build()

        when (effect) {
            SplashEffect.NavigateToMain -> {
                val action = SplashFragmentDirections.actionSplashFragmentToMainGraphActivity()
                navigate(
                    action,
                    extras,
                )
            }

            SplashEffect.NavigateToAuth -> {
                val action = SplashFragmentDirections.actionSplashFragmentToAuthActivity()
                navigate(
                    action,
                    extras,
                )
            }
        }
    }
}
