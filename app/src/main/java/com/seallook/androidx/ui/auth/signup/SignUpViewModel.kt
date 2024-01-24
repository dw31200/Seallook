package com.seallook.androidx.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.SetProfileUseCase
import com.seallook.androidx.domain.usecase.SignUpUseCase
import com.seallook.androidx.domain.usecase.usertype.UpdateUserTypeUseCase
import com.seallook.androidx.share.Gender
import com.seallook.androidx.share.UserTypeOption
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
    private val updateUserTypeUseCase: UpdateUserTypeUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<SignUpEffect>() {
    val signUpType = savedStateHandle.get<UserTypeOption>("selectSignUpType")

    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    val name = MutableLiveData<String>()
    private val _nameValidation = name.map {
        it != null && it.isNotBlank() && it.isNotEmpty() && it.length >= 2
    }

    val gender = MutableLiveData<Gender>()
    private val _genderValidation = gender.map {
        it != Gender.NONE && it != null
    }

    val birth = MutableLiveData<Date?>()
    private val _birthValidation = birth.map {
        it != null && it.toString().isNotBlank() && it.toString().isNotEmpty()
    }

    val email = MutableLiveData<String>()
    private val _emailValidation = email.map {
        it != null && it.isNotBlank() && it.isNotEmpty()
    }

    val password = MutableLiveData<String>()
    private val _passwordValidation = password.map {
        it?.isNotBlank() == true && it?.isNotEmpty() == true && it != null
    }

    val passwordConfirmation = MutableLiveData<String>()
    private val _passwordConfirmationValidation = passwordConfirmation.map {
        it?.isNotBlank() == true && it?.isNotEmpty() == true && it != null
    }

    val over14yoChecked = MutableLiveData<Boolean>()

    val privacyPolicyChecked = MutableLiveData<Boolean>()

    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?>
        get() = _passwordError

    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?>
        get() = _emailError

    val validation = MediatorLiveData<Boolean>()

    private val _validationList = mutableListOf<LiveData<Boolean>>()

    init {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase()
            name.value = currentUser.value?.displayName
            email.value = currentUser.value?.email
        }

        _validationList.add(_nameValidation)
        validation.addSource(_nameValidation) {
            validateFields()
        }

        _validationList.add(_emailValidation)
        validation.addSource(_emailValidation) {
            validateFields()
        }

        _validationList.add(_genderValidation)
        validation.addSource(_genderValidation) {
            validateFields()
        }

        _validationList.add(_birthValidation)
        validation.addSource(_birthValidation) {
            validateFields()
        }

        if (currentUser.value == null) {
            _validationList.add(_passwordValidation)
            validation.addSource(_passwordValidation) {
                validateFields()
            }

            _validationList.add(_passwordConfirmationValidation)
            validation.addSource(_passwordConfirmationValidation) {
                validateFields()
            }
        }

        _validationList.add(over14yoChecked)
        validation.addSource(over14yoChecked) {
            validateFields()
        }

        _validationList.add(privacyPolicyChecked)
        validation.addSource(privacyPolicyChecked) {
            validateFields()
        }
    }

    private fun validateFields() {
        validation.value = _validationList.all {
            it.value == true
        }
    }

    fun signUp() {
        setEffect(SignUpEffect.SignUp)
        _passwordError.value = null
        _emailError.value = null
        val profile = ProfileUiModel(
//            sdw312 임시 테스트
            0,
            email.value ?: return,
            name.value,
            gender.value?.ordinal,
            birth.value,
            Date(),
            signUpType?.ordinal,
        )
        if (currentUser.value != null) {
            setProfile(profile)
        } else {
            viewModelScope.launch {
                signUpUseCase(
                    SignUpUseCase.Params(
                        email.value,
                        password.value,
                    ),
                )
                    .onSuccess {
                        setProfile(profile)
                    }
                    .onFailure {
                        setEffect(SignUpEffect.FailureSignUp)
                        when (it) {
                            is FirebaseAuthWeakPasswordException -> {
                                _passwordError.value =
                                    "안전한 비밀번호가 아닙니다. 길이가 6자 이상이어야 합니다."
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

    private fun setProfile(profile: ProfileUiModel) {
        viewModelScope.launch {
            getCurrentUserUseCase()?.uid?.let {
                setProfileUseCase(it, profile.toDomainModel())
                    .onSuccess {
                        updateUserTypeUseCase(
                            UpdateUserTypeUseCase.Params(
                                profile.email,
                                profile.userType,
                            ),
                        )
                            .onSuccess {
                                setEffect(SignUpEffect.NavigateToHome)
                            }
                            .onFailure {
                                setEffect(SignUpEffect.FailureSignUp)
                            }
                    }
                    .onFailure {
                        setEffect(SignUpEffect.FailureSetProfile)
                    }
            }
        }
    }
}
