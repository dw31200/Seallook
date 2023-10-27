package com.seallook.androidx.ui.auth.signup

import androidx.lifecycle.LiveData
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
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val setProfileUseCase: SetProfileUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {
    val signUpType = savedStateHandle.get<UserType>("selectSignUpType")
    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser
    private val _passwordError = MutableLiveData<String?>()
    val passwordError: LiveData<String?>
        get() = _passwordError
    private val _emailError = MutableLiveData<String?>()
    val emailError: LiveData<String?>
        get() = _emailError

    init {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase()
        }
    }

    fun signUp(profile: ProfileUiModel, password: String) {
        viewModelScope.launch {
            try {
                signUpUseCase(profile.toDomainModel(), password)
            } catch (e: Exception) {
                when (e) {
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

    fun setProfile(profile: ProfileUiModel) {
        viewModelScope.launch {
            currentUser.value?.uid?.let {
                setProfileUseCase(it, profile.toDomainModel())
            }
        }
    }
}
