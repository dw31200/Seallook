package com.seallook.androidx.ui.auth.signup

import android.text.format.DateUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.seallook.androidx.BR
import com.seallook.androidx.BuildConfig
import com.seallook.androidx.R
import com.seallook.androidx.databinding.FragmentSignUpBinding
import com.seallook.androidx.domain.model.ProfileEntity
import com.seallook.androidx.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

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
    FragmentSignUpBinding::inflate,
) {
    override val viewModel: SignUpViewModel by viewModels()
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
        }
    }
    private var birth: Date? = null
        set(value) {
            field = value
            if (value != null) {
                binding.birthTextField.editText!!.setText(
                    DateUtils.formatDateTime(
                        requireContext(),
                        value.time,
                        DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_NUMERIC_DATE,
                    ),
                )
            }
        }

    override fun viewModelVariableId(): Int = BR.vm

    override fun onViewCreatedAfterBinding() {
        with(binding) {
            if (isSignedIn()) {
                requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

                with(emailTextField) {
                    editText!!.imeOptions = EditorInfo.IME_ACTION_DONE
                    editText!!.setOnEditorActionListener { _, actionId, _ ->
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            if (validateFields()) signUp()
                        }

                        return@setOnEditorActionListener false
                    }
                }
            } else {
                passwordConfirmationTextField.editText!!.setOnEditorActionListener { _, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        if (validateFields()) signUp()
                    }

                    return@setOnEditorActionListener false
                }
            }

            listOf(
                nameTextField,
                birthTextField,
                emailTextField,
                passwordTextField,
                passwordConfirmationTextField,
            ).forEach { textField ->
                textField.editText!!.addTextChangedListener {
                    signUpButton.isEnabled = validateFields()

                    textField.error = null
                    textField.isErrorEnabled = false
                }
            }

            genderButtonGroup.addOnButtonCheckedListener { _, _, _ ->
                signUpButton.isEnabled = validateFields()
            }

            listOf(over14yoCheckbox, privacyPolicyCheckbox).forEach {
                it.setOnCheckedChangeListener { _, _ ->
                    signUpButton.isEnabled = validateFields()
                }
            }

            birthTextField.editText!!.setOnClickListener {
                showDatePicker()
            }

            birthTextField.setEndIconOnClickListener {
                showDatePicker()
            }

            signUpButton.setOnClickListener { signUp() }
        }
    }

    private fun isSignedIn(): Boolean {
        return Firebase.auth.currentUser != null
    }

    private fun validateFields(): Boolean = with(binding) {
        val validation =
            nameTextField.editText!!.text.toString().isNotBlank() &&
                genderButtonGroup.checkedButtonId != View.NO_ID &&
                birthTextField.editText!!.text.toString().isNotBlank() &&
                emailTextField.editText!!.text.toString().isNotBlank() &&
                over14yoCheckbox.isChecked &&
                privacyPolicyCheckbox.isChecked

        if (isSignedIn()) {
            return validation
        }

        return validation &&
            passwordTextField.editText!!.text.toString().isNotBlank() &&
            passwordConfirmationTextField.editText!!.text.toString().isNotBlank()
    }

    private fun showDatePicker() {
        val selection = if (birth == null) Date().toUtc() else birth!!.toUtc()
        val picker = MaterialDatePicker.Builder.datePicker()
            .setSelection(selection)
            .build()
            .apply {
                addOnPositiveButtonClickListener {
                    this@SignUpFragment.birth = it.toLocal()
                }
            }
        picker.show(childFragmentManager, "date_picker")
    }

    private fun signUp() {
        if (isProgressDialogShown()) return
        val name = binding.nameTextField.editText!!.text.toString().trim()
        val gender =
            if (binding.genderButtonGroup.checkedButtonId == R.id.male_button) 0 else 1
        val email = binding.emailTextField.editText!!.text.toString().trim()
        val password = binding.passwordTextField.editText!!.text.toString().trim()
        val passwordConfirmation =
            binding.passwordConfirmationTextField.editText!!.text.toString().trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailTextField.error = "이메일 주소를 올바르게 입력해 주세요."
            return
        }

        if (!isSignedIn()) {
            if (password != passwordConfirmation) {
                binding.passwordConfirmationTextField.error = "비밀번호가 일치하지 않습니다."
                return
            }

            if (!BuildConfig.DEBUG) {
                val letterCount = password.count { it.isLetter() }
                val digitCount = password.count { it.isDigit() }
                val specialCount = password.count { !it.isLetterOrDigit() }

                if (password.length < 8 || letterCount == 0 || digitCount == 0 || specialCount == 0) {
                    binding.passwordTextField.error =
                        "안전한 비밀번호가 아닙니다. 길이가 8자 이상이고 영어와 숫자, 특수문자가 조합되어야 합니다."
                    return
                }
            }
        }

        if (isSignedIn()) {
            showProgressDialog("프로필 생성 중... 잠시만 기다려 주세요.")
            val profile = ProfileEntity(
                "",
                email,
                name,
                gender,
                birth!!,
                Date(),
            )
            viewModel.setProfile(profile)
            viewModel.setUserType()
        } else {
            showProgressDialog("회원가입 중... 잠시만 기다려 주세요.")
            val profile = ProfileEntity(
                "",
                email,
                name,
                gender,
                birth!!,
                Date(),
            )
            viewModel.signUp(profile, password)
            viewModel.signUpResult.observe(viewLifecycleOwner) {
                if (it != null) {
                    viewModel.setProfile(profile)
                    viewModel.setUserType()
                    findNavController().navigate(
                        R.id.action_signUpFragment_to_mainGraphActivity,
                        null,
                        navOptions {
                            popUpTo(R.id.nav_graph)
                        },
                    )
                    dismissProgressDialog()
                } else {
                    dismissProgressDialog()
                }
            }
        }
    }

    override fun onDestroy() {
        onBackPressedCallback.remove()

        super.onDestroy()
    }

    private fun Date.toUtc(): Long {
        val local = Calendar.getInstance().apply { time = this@toUtc }
        val utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"))

        utc.set(
            local.get(Calendar.YEAR),
            local.get(Calendar.MONTH),
            local.get(Calendar.DAY_OF_MONTH),
            0,
            0,
            0,
        )
        return utc.timeInMillis
    }

    private fun Long.toLocal(): Date {
        val local = Calendar.getInstance()
        val utc = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
            timeInMillis = this@toLocal
        }

        local.set(
            utc.get(Calendar.YEAR),
            utc.get(Calendar.MONTH),
            utc.get(Calendar.DAY_OF_MONTH),
            0,
            0,
            0,
        )
        return local.time
    }
}
