package com.seallook.androidx.ui.auth.signup.select

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentSelectSignUpBinding
import com.seallook.androidx.share.UserTypeOption
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect

/* TODO
    1.InitView: 내담자, 상담사, 기관 별 가입 버튼 보여주기
    2.Navigation: 각 버튼 클릭시 SignUp으로 이동
 */
class SelectSignUpTypeFragment :
    BaseFragment<FragmentSelectSignUpBinding, SelectSignUpTypeViewModel, Effect>(
        FragmentSelectSignUpBinding::inflate,
    ),
    SelectSignUpTypeNavigation {
    override val viewModel: SelectSignUpTypeViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        binding.navigation = this@SelectSignUpTypeFragment
    }

    override fun onEffectCollect(effect: Effect) = Unit

    override fun navigateToSignUp(selectSignUpType: UserTypeOption) {
        val action = SelectSignUpTypeFragmentDirections.actionSelectSignUpTypeFragmentToSignUpFragment(selectSignUpType)
        navigate(action)
    }
}
