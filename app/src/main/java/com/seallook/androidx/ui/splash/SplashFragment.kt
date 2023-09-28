package com.seallook.androidx.ui.splash

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.databinding.FragmentSplashBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(
    FragmentSplashBinding::inflate,
) {
    override val viewModel: SplashViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        viewModel.currentUser.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(
                    R.id.action_splashFragment_to_mainGraphActivity,
                    null,
                    navOptions {
                        launchSingleTop = true
                        popUpTo(R.id.splash_navigation)
                    },
                )
            } else {
                findNavController().navigate(
                    R.id.action_splashFragment_to_signInFragment,
                    null,
                    navOptions {
                        launchSingleTop = true
                        popUpTo(R.id.splash_navigation)
                    },
                )
            }
        }
    }
}
