package com.seallook.androidx.ui.auth.signup

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.ActivityNavigator
import androidx.navigation.fragment.findNavController
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentSignUpBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1. 모든 코드 viewmodel로 이동
    2. 각 입력 필드 리스너 binding으로 뷰모델에서 직접 옵저빙
    3. signUpUseCase 파라미터 profile에서 email로 변경
    4. 프로그래스 관련 binding으로 변경
    5. 백버튼 관련 제거
    필요한 모델: Uid(userEmail, uid), Profile(id > uid로 변경), UserType(userEmail, userType)
 */
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
                findNavController().navigate(
                    SignUpFragmentDirections.actionSignUpFragmentToMainGraphActivity(),
                    extras,
                )
            }
        }
    }
}
