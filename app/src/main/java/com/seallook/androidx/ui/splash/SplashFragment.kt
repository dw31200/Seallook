package com.seallook.androidx.ui.splash

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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

    private val auth: FirebaseAuth = Firebase.auth

    override fun onViewCreatedAfterBinding() {
        if (auth.currentUser != null) {
            findNavController().navigate(R.id.action_splashFragment_to_mainGraphActivity)
        } else {
            findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
        }
    }
}
