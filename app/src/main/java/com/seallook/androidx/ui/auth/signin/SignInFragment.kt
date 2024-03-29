package com.seallook.androidx.ui.auth.signin

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.ActivityNavigator
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.seallook.androidx.BR
import com.seallook.androidx.R
import com.seallook.androidx.databinding.FragmentSignInBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SignInFragment :
    BaseFragment<FragmentSignInBinding, SignInViewModel, SignInEffect>(
        FragmentSignInBinding::inflate,
    ),
    SignInNavigation {
    override val viewModel: SignInViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    private val googleSignInClient: GoogleSignInClient by lazy { getGoogleClient() }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)
            account?.idToken?.let {
                viewModel.signInWithGoogle(it)
            }
        } catch (e: ApiException) {
            Timber.e(e.stackTraceToString())
        }
    }

    private val extras = ActivityNavigator.Extras.Builder()
        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        .build()

    override fun onViewCreatedAfterBinding() {
        binding.navigation = this
    }

    override fun onEffectCollect(effect: SignInEffect) {
        when (effect) {
            SignInEffect.NavigateToSignUp -> {
                dismissProgressDialog()
                val action = SignInFragmentDirections.actionSignInFragmentToSelectSignUpTypeFragment()
                navigate(action)
            }
            SignInEffect.FailureSignIn -> {
                dismissProgressDialog()
                showFailMessage("로그인에 실패했습니다.")
            }
            SignInEffect.FailureSignInWithEmailAndPassword -> {
                dismissProgressDialog()
                showFailMessage("로그인에 실패했습니다.")
            }
            SignInEffect.FailureSignInWithGoogle -> {
                dismissProgressDialog()
                showFailMessage("로그인에 실패했습니다.")
            }
            SignInEffect.SignInWithEmailAndPassword -> {
                showProgressDialog("로그인 중입니다.")
            }
            SignInEffect.SignInWithGoogle -> {
                showProgressDialog("로그인 중입니다.")
            }
            SignInEffect.SuccessSignIn -> {
                dismissProgressDialog()
                val action = SignInFragmentDirections.actionSignInFragmentToMainGraphActivity()
                navigate(action, extras)
            }
            SignInEffect.SuccessSignInWithEmailAndPassword -> Unit
            SignInEffect.SuccessSignInWithGoogle -> Unit
        }
    }

    private fun getGoogleClient(): GoogleSignInClient {
        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.google_web_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(requireActivity(), googleSignInOption)
    }

    override fun signInWithGoogle() {
        googleSignInClient.signOut()
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    override fun navigateToSelectSignUpType() {
        val action = SignInFragmentDirections.actionSignInFragmentToSelectSignUpTypeFragment()
        navigate(action)
    }
}
