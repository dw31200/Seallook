package com.seallook.androidx.ui.auth.signin

import android.content.Intent
import android.content.IntentSender
import android.util.Patterns
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.ActivityNavigator
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
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
class SignInFragment :
    BaseFragment<FragmentSignInBinding, SignInViewModel>(
        FragmentSignInBinding::inflate,
    ),
    SignInNavigation {
    override val viewModel: SignInViewModel by viewModels()
    override fun viewModelVariableId(): Int = BR.vm

    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest
    private val extras = ActivityNavigator.Extras.Builder()
        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        .build()

    override fun onViewCreatedAfterBinding() {
        binding.navigation = this
        oneTapClient = Identity.getSignInClient(requireActivity())
        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(getString(R.string.google_web_client_id))
                    .setFilterByAuthorizedAccounts(false)
                    .build(),
            )
            .build()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_ONE_TAP -> {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
                    val idToken = credential.googleIdToken
                    val username = credential.id
                    val password = credential.password
                    when {
                        idToken != null -> {
                            viewModel.signInWithGoogle(idToken)
                            Timber.d("Got ID token.")
                        }

                        password != null -> {
                            Timber.d("Got password.")
                        }

                        else -> {
                            Timber.d("No ID token or password!")
                        }
                    }
                } catch (e: ApiException) {
                    Timber.d(e.localizedMessage)
                }
            }
        }
    }

    override fun signInWithEmailAndPassword() {
        if (isSigningIn()) return
        val email = binding.emailTextField.editText!!.text.toString().trim()
        val password = binding.passwordTextField.editText!!.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailTextField.error = "이메일 주소를 올바르게 입력해 주세요."
            return
        }

        startSignIn()
        viewModel.signInWithEmailAndPassword(email, password)
    }

    override fun signInWithGoogle() {
        if (isSigningIn()) return

        startSignIn()
        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener(requireActivity()) { result ->
                try {
                    startIntentSenderForResult(
                        result.pendingIntent.intentSender,
                        REQ_ONE_TAP,
                        null,
                        0,
                        0,
                        0,
                        null,
                    )
                } catch (e: IntentSender.SendIntentException) {
                    Timber.d("Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }
            .addOnFailureListener(requireActivity()) { e ->
                Timber.d(e.localizedMessage)
            }
    }

    private fun startSignIn() {
        showProgressDialog("로그인 중... 잠시만 기다려 주세요.")
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

    override fun navigateToSelectSignUpType() {
        findNavController().navigate(R.id.action_signInFragment_to_selectSignUpTypeFragment)
    }

    companion object {
        private const val REQ_ONE_TAP = 1001
    }
}
