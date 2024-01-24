package com.seallook.androidx.ui.auth.signup

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.ActivityNavigator
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentSignUpBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment :
    BaseFragment<FragmentSignUpBinding, SignUpViewModel, SignUpEffect>(
        FragmentSignUpBinding::inflate,
    ) {
    override val viewModel: SignUpViewModel by viewModels()
    private val extras = ActivityNavigator.Extras.Builder()
        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        .build()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() = Unit

    override fun onEffectCollect(effect: SignUpEffect) {
        when (effect) {
            SignUpEffect.NavigateToHome -> {
                dismissProgressDialog()
                val action = SignUpFragmentDirections.actionSignUpFragmentToMainGraphActivity()
                navigate(
                    action,
                    extras,
                )
            }

            SignUpEffect.FailureSetProfile -> {
                dismissProgressDialog()
                showFailMessage("회원가입에 실패했습니다.")
            }
            SignUpEffect.FailureSignUp -> {
                dismissProgressDialog()
                showFailMessage("회원가입에 실패했습니다.")
            }
            SignUpEffect.SignUp -> {
                showProgressDialog("회원가입 중입니다.")
            }
        }
    }
}
