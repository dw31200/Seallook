package com.seallook.androidx.ui.auth.signin

import android.app.Activity
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.databinding.FragmentSignInBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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
class SignInFragment : BaseFragment<FragmentSignInBinding, SignInViewModel>(
    FragmentSignInBinding::inflate,
) {
    override val viewModel: SignInViewModel by viewModels()
    override fun viewModelVariableId(): Int = BR.vm

    private val oneTapClient by lazy { Identity.getSignInClient(requireContext()) }
    private val googleSignInIntentResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
            if (it.resultCode != Activity.RESULT_OK) {
                Timber.d("${it.resultCode}")
                cancelSignIn()
                return@registerForActivityResult
            }
            val credential = oneTapClient.getSignInCredentialFromIntent(it.data)
            val idToken = credential.googleIdToken

            if (idToken != null) {
                viewModel.signInWithGoogle(idToken)
            } else {
                failSignIn()
            }
        }

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            googleSignInButton.setOnClickListener {
                signInWithGoogle()
            }
            emailSignInButton.setOnClickListener {
                signInWithEmailAndPassword()
            }
            emailSignUpButton.setOnClickListener {
                findNavController().navigate(R.id.action_signInFragment_to_selectSignUpTypeFragment)
            }
            viewModel.signInWithGoogleResult.observe(viewLifecycleOwner) {
                if (it != null) {
                    viewModel.getCurrentUser()
                    viewModel.currentUser.observe(viewLifecycleOwner) {
                        if (it != null) {
                            viewModel.getProfile(it)
                            navigation()
                            cancelSignIn()
                        }
                    }
                }
            }
        }
    }

    private fun signInWithEmailAndPassword() {
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

    private fun signInWithGoogle() {
        if (isSigningIn()) return

        startSignIn()
        viewModel.getBeginSignInResult()
        viewModel.beginSignInResult.observe(viewLifecycleOwner) {
            if (it != null) {
                googleSignInIntentResultLauncher.launch(
                    IntentSenderRequest.Builder(it.pendingIntent.intentSender).build(),
                )
            }
        }
    }

    private fun startSignIn() {
        showProgressDialog("로그인 중... 잠시만 기다려 주세요.")
    }

    private fun navigation() {
        viewModel.profile.observe(viewLifecycleOwner) {
            if (it != null) {
                findNavController().navigate(R.id.action_signInFragment_to_mainGraphActivity)
            } else {
                findNavController().navigate(R.id.action_signInFragment_to_selectSignUpTypeFragment)
            }
        }
    }

    private fun cancelSignIn() {
        dismissProgressDialog()
    }

    private fun failSignIn() {
        dismissProgressDialog()

        Toast.makeText(
            requireContext(),
            "오류가 발생하였습니다. 잠시 후 다시 시도해 주세요.",
            Toast.LENGTH_SHORT,
        ).show()
    }

    private fun isSigningIn() = isProgressDialogShown()
}
