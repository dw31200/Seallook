package com.seallook.androidx.ui.auth.signin

import android.app.Activity
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.databinding.DialogProgressBinding
import com.seallook.androidx.databinding.FragmentSignInBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
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

    private var progressDialog: AlertDialog? = null
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
                        failSignIn(result)
                    }
                }
            } else {
                cancelSignIn()
            }
        }

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            googleSignInButton.setOnClickListener {
                lifecycleScope.launch {
                    signInWithGoogle()
                }
            }
        }
    }

    private suspend fun signInWithGoogle() = coroutineScope {
        if (isSigningIn()) return@coroutineScope

        startSignIn()

        try {
            val result = oneTapClient.beginSignIn(signInRequest).await()
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

    private fun cancelSignIn() {
        dismissProgressDialog()
    }

    private fun showProgressDialog(message: String, enforce: Boolean = false) {
        if (enforce) {
            dismissProgressDialog()
        } else {
            if (isProgressDialogShown()) return
        }
        val binding = LayoutInflater.from(requireContext()).let {
            DialogProgressBinding.inflate(it)
        }

        binding.messageTextView.text = message

        progressDialog = MaterialAlertDialogBuilder(requireContext())
            .setView(binding.root)
            .setCancelable(false)
            .create()
        progressDialog!!.show()
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
        progressDialog = null
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
    private fun isProgressDialogShown() = progressDialog != null
}
