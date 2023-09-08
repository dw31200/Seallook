package com.seallook.androidx.ui.auth.signup

import androidx.fragment.app.viewModels
import com.seallook.androidx.BR
import com.seallook.androidx.databinding.FragmentSignUpBinding
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/* TODO
    1.GetSelectType: 선택한 가입 유형 가져오기
    2.InitView: 선택한 가입 유형에 따른 화면 보여주기
    3.Navigation: SignUp 버튼 클릭시 개인 인증 화면으로 이동
    4.GetAuth: 개인 인증 승인을 획득한다.
    5.SignUp: 개인 인증 승인이 확인되었으면, SignUp 버튼 클릭시 계정 정보를 업로드한다.
    6.Navigation: SignUp 클릭시 SignIn으로 이동
 */
@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>(
    FragmentSignUpBinding::inflate
) {
    override val viewModel: SignUpViewModel by viewModels()

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        Unit
    }
}
