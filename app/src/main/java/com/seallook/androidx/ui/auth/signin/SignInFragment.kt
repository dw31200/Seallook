package com.seallook.androidx.ui.auth.signin

import android.content.Intent
import android.content.IntentSender
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
        viewModel.navigateToSignUp.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSelectSignUpTypeFragment())
            }
        }
        viewModel.navigateToHome.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainGraphActivity(), extras)
            }
        }
    }

    //    TODO startIntentSenderForResult 와 onActivityResult 를 bindingadapter에 어떻게 옮겨야 할까요?
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_ONE_TAP -> {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
                    val idToken = credential.googleIdToken
                    when {
                        idToken != null -> {
                            viewModel.signInWithGoogle(idToken)
                            Timber.d("Got ID token.")
                        }

                        else -> {
                            Timber.d("No ID token")
                        }
                    }
                } catch (e: ApiException) {
                    Timber.d(e.localizedMessage)
                }
            }
        }
    }

    override fun signInWithGoogle() {
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

    override fun navigateToSelectSignUpType() {
        findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSelectSignUpTypeFragment())
    }

    companion object {
        private const val REQ_ONE_TAP = 1001
    }
}
