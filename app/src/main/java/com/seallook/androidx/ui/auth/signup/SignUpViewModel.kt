package com.seallook.androidx.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.SetProfileUseCase
import com.seallook.androidx.domain.usecase.SignUpUseCase
import com.seallook.androidx.share.UserType
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.ProfileUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val setProfileUseCase: SetProfileUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<SignUpEffect>() {
    val signUpType = savedStateHandle.get<UserType>("selectSignUpType")

    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    val name = MutableLiveData<String>()

    val gender = MutableLiveData<Int>()

    val birth = MutableLiveData<Date?>()

    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    val passwordConfirmation = MutableLiveData<String>()

    val over14yoChecked = MutableLiveData<Boolean>()

    val privacyPolicyChecked = MutableLiveData<Boolean>()

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?>
        get() = _passwordError

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?>
        get() = _emailError

    val validation: MediatorLiveData<Boolean> = MediatorLiveData()

    private val _progressMessage = MutableLiveData<String>()
    val progressMessage: LiveData<String>
        get() = _progressMessage

    private val _isShowProgress = MutableLiveData<Boolean>()
    val isShowProgress: LiveData<Boolean>
        get() = _isShowProgress

    private val _isShowFailMessage = MutableLiveData<String>()
    val isShowFailMessage: LiveData<String>
        get() = _isShowFailMessage

    init {
        validation.addSource(name) {
            validation.value = validateFields()
        }
        validation.addSource(email) {
            validation.value = validateFields()
        }
        validation.addSource(gender) {
            validation.value = validateFields()
        }
        validation.addSource(birth) {
            validation.value = validateFields()
        }
        validation.addSource(password) {
            validation.value = validateFields()
        }
        validation.addSource(passwordConfirmation) {
            validation.value = validateFields()
        }
        validation.addSource(over14yoChecked) {
            validation.value = validateFields()
        }
        validation.addSource(privacyPolicyChecked) {
            validation.value = validateFields()
        }
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase()
            name.value = currentUser.value?.displayName
            email.value = currentUser.value?.email
            _progressMessage.value = "회원가입 중... 잠시만 기다려 주세요."
        }
    }

    private fun validateFields(): Boolean {
        val validation =
            name.value?.isNotBlank() == true &&
                gender.value?.toString()?.isNotBlank() == true &&
                birth.value?.toString()?.isNotBlank() == true &&
                email.value?.isNotBlank() == true &&
                over14yoChecked.value == true &&
                privacyPolicyChecked.value == true

        if (currentUser.value != null) {
            return validation
        }

        return validation &&
            password.value?.isNotBlank() == true &&
            passwordConfirmation.value?.isNotBlank() == true
    }

    fun signUp() {
        val profile = ProfileUiModel(
            0,
            email.value ?: "",
            name.value ?: "",
            gender.value ?: 0,
            birth.value ?: Date(),
            Date(),
            signUpType?.ordinal ?: 0,
        )
        if (currentUser.value != null) {
            setProfile(profile)
        } else {
            viewModelScope.launch {
                _isShowProgress.value = true
                signUpUseCase(profile.toDomainModel(), password.value ?: "")
                    .onSuccess {
                        setProfile(profile)
                    }
                    .onFailure {
                        when (it) {
                            is FirebaseAuthWeakPasswordException -> {
                                _passwordError.value =
                                    "안전한 비밀번호가 아닙니다. 길이가 8자 이상이고 영어와 숫자, 특수문자가 조합되어야 합니다."
                            }

                            is FirebaseAuthInvalidCredentialsException -> {
                                _emailError.value = "이메일 주소를 올바르게 입력해 주세요."
                            }

                            is FirebaseAuthUserCollisionException -> {
                                _emailError.value = "이미 사용중인 이메일 주소 입니다."
                            }

                            else -> {
                                _passwordError.value = "오류가 발생하였습니다. 잠시 후 다시 시도해 주세요."
                            }
                        }
                    }
            }
        }
    }

    fun setProfile(profile: ProfileUiModel) {
        viewModelScope.launch {
            getCurrentUserUseCase()?.uid?.let {
                setProfileUseCase(it, profile.toDomainModel())
                    .onSuccess {
                        _isShowProgress.value = false
                        setEffect(SignUpEffect.NavigateToHome)
                    }
                    .onFailure {
                        Unit
                    }
            }
        }
    }
}
