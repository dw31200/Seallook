package com.seallook.androidx.ui.auth.signin

import android.util.Patterns
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.databinding.FragmentSignInBinding
import com.seallook.androidx.ui.base.auth.SignInBaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/* TODO
    0.InitView: id,password EditText, 각 로그인 방식 버튼, 회원가입 버튼, 비밀번호 리겟 버튼 보여주기
    1.GetAuth: 로그인 버튼 클릭시 해당 로그인 방식에 따른 Auth 인증 가져오기
    2.GetAuthType: 획득한 Auth의 Type 가져오기
    3.NavigateToSelect: 획득한 Auth의 Type이 Counseler 일 경우 SelectSignIn으로 이동
    4.NavigateToHome: 획득한 Auth의 Type이 Counseler가 아닐 경우 Home으로 이동
    5.NavigateToReset: 비밀번호 리셋 클릭시 ResetPassword로 이동
    6.NavigateToSighUp: 회원가입 클릭시 SelectSignUpType으로 이동
 */

@AndroidEntryPoint
class SignInFragment : SignInBaseFragment<FragmentSignInBinding>(
    FragmentSignInBinding::inflate,
) {
    override val viewModel: SignInViewModel by viewModels()
    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            googleSignInButton.setOnClickListener {
                signInWithGoogle()
            }
            emailSignInButton.setOnClickListener {
                lifecycleScope.launch {
                    signInWithEmailAndPassword()
                }
            }
            emailSignUpButton.setOnClickListener {
                findNavController().navigate(R.id.action_signInFragment_to_selectSignUpTypeFragment)
            }
            viewModel.profile.observe(viewLifecycleOwner) {
                if (it != null) {
                    findNavController().navigate(R.id.action_signInFragment_to_mainGraphActivity)
                } else {
                    findNavController().navigate(R.id.action_signInFragment_to_selectSignUpTypeFragment)
                }
            }
            viewModel.signInWithGoogleResult.observe(viewLifecycleOwner) {
                if (it != null) {
                    navigation()
                    cancelSignIn()
                }
            }
        }
    }

    private fun signInWithEmailAndPassword() {
//        private suspend fun signInWithEmailAndPassword() = coroutineScope {
        if (isSigningIn()) return
        val email = binding.emailTextField.editText!!.text.toString().trim()
        val password = binding.passwordTextField.editText!!.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailTextField.error = "이메일 주소를 올바르게 입력해 주세요."
            return
        }

        startSignIn()
        viewModel.signInWithEmailAndPassword(email, password)
        viewModel.signInWithEmailResult.observe(viewLifecycleOwner) {
            if (it != null) {
                if (it is FirebaseAuthInvalidCredentialsException) {
                    binding.passwordTextField.error = "이메일 혹은 비밀번호가 일치하지 않습니다. 다시 입력해 주세요."
                } else {
                    findNavController().navigate(R.id.action_signInFragment_to_mainGraphActivity)
                }

                cancelSignIn()
            }
        }
    }
}
