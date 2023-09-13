package com.seallook.androidx.ui.base.auth

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.AuthResult
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.ui.auth.signin.SignInViewModel
import com.seallook.androidx.ui.base.BaseFragment
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class SignInBaseFragment<T : ViewDataBinding>(
    inflate: (
        LayoutInflater,
        ViewGroup?,
        Boolean,
    ) -> T,
) : BaseFragment<T, SignInViewModel>(
    inflate,
) {
    abstract override val viewModel: SignInViewModel
    override fun viewModelVariableId(): Int = BR.vm

    private val oneTapClient by lazy { Identity.getSignInClient(requireContext()) }
    private val signInRequest by lazy {
        BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(getString(R.string.google_web_client_id))
                    .setFilterByAuthorizedAccounts(false)
                    .build(),
            )
            .setAutoSelectEnabled(false)
            .build()
    }
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
                lifecycleScope.launch {
                    val result = viewModel.signInWithGoogle(idToken)
                    if (result != null) {
                        navigation(result)
                        cancelSignIn()
                    }
                }
            } else {
                cancelSignIn()
            }
        }

    protected suspend fun signInWithGoogle() = coroutineScope {
        if (isSigningIn()) return@coroutineScope

        startSignIn()

        try {
            val result = viewModel.getBeginSignInResult()
            googleSignInIntentResultLauncher.launch(
                IntentSenderRequest.Builder(result.pendingIntent.intentSender).build(),
            ).also { Timber.d("launch sign") }
        } catch (e: Exception) {
            failSignIn(e)
        }
    }

    private fun startSignIn() {
        showProgressDialog("로그인 중... 잠시만 기다려 주세요.")
    }

    private fun navigation(result: AuthResult) {
        if (result.user?.displayName == "d song") {
            findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
        } else {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    private fun cancelSignIn() {
        dismissProgressDialog()
    }

    private fun failSignIn(e: Throwable) {
        dismissProgressDialog()

        e.printStackTrace()

        Toast.makeText(
            requireContext(),
            "오류가 발생하였습니다. 잠시 후 다시 시도해 주세요.",
            Toast.LENGTH_SHORT,
        ).show()
    }

    private fun isSigningIn() = isProgressDialogShown()
}
