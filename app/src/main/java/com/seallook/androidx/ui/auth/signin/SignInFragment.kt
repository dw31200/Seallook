package com.seallook.androidx.ui.auth.signin

import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.ActivityNavigator
import androidx.navigation.fragment.findNavController
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

/* TODO
    1. 구글 로그인 로직 registerForActivityResult 빼고 뷰모델 혹은 도메인 아랫단으로 옮기기
    2. 관련 UseCase 반환값 Result로 변환
    3. Effect로 네비 처리
    4. 이메일 및 비밀번호 공란일 때 버튼 비활성화
    5. 로그인 성공시 유저 uid 데이터베이스에 저장
    필요한 모델: Profile(id를 Int에서 uid string으로 변경),Uid(userEmail, uid)
 */

@AndroidEntryPoint
class SignInFragment :
    BaseFragment<FragmentSignInBinding, SignInViewModel>(
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
        findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSelectSignUpTypeFragment())
    }
}
