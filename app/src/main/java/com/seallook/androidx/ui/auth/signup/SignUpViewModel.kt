package com.seallook.androidx.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.model.ProfileEntity
import com.seallook.androidx.domain.model.UserTypeEntity
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.GetProfileUseCase
import com.seallook.androidx.domain.usecase.SetProfileUseCase
import com.seallook.androidx.domain.usecase.SetUserTypeUseCase
import com.seallook.androidx.domain.usecase.SignOutUseCase
import com.seallook.androidx.domain.usecase.SignUpUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val setProfileUseCase: SetProfileUseCase,
    private val setUserTypeUseCase: SetUserTypeUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {
    private val _profile = MutableLiveData<ProfileEntity?>()
    val profile: LiveData<ProfileEntity?>
        get() = _profile
    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser
    val signUpType = savedStateHandle.getStateFlow("selectSignUpType", 0)
    private val _signUpResult = MutableLiveData<AuthResult?>()
    val signUpResult: LiveData<AuthResult?>
        get() = _signUpResult
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
    fun signUp(profile: ProfileEntity, password: String?) {
        viewModelScope.launch {
            try {
                _signUpResult.value = signUpUseCase(profile, password)
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
                _signUpResult.value = null
            }
        }
    }

    fun setProfile(user: FirebaseUser?, profile: ProfileEntity) {
        viewModelScope.launch {
            setProfileUseCase(user, profile.toProfile())
        }
    }

    fun setUserType() {
        viewModelScope.launch {
            setUserTypeUseCase(currentUser.value, UserTypeEntity(signUpType.value))
        }
    }

    fun signOut() {
        viewModelScope.launch {
            signOutUseCase()
        }
    }
}
