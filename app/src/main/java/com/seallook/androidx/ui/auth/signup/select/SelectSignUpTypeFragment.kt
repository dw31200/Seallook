package com.seallook.androidx.ui.auth.signup.select

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentSelectSignUpBinding
import com.seallook.androidx.share.UserTypeOption
import com.seallook.androidx.ui.base.BaseFragment
import com.seallook.androidx.ui.base.Effect

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
