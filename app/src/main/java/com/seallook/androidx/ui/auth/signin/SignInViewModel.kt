package com.seallook.androidx.ui.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.usecase.GetProfileUseCase
import com.seallook.androidx.domain.usecase.SignInWithEmailAndPasswordUseCase
import com.seallook.androidx.domain.usecase.SignInWithGoogleUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// Event
// Effect

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
    private val getProfileUseCase: GetProfileUseCase,
) : BaseViewModel<SignInEffect>() {
    private val _progressMessage = MutableLiveData<String>()
    val progressMessage: LiveData<String>
        get() = _progressMessage

    private val _isShowProgress = MutableLiveData<Boolean>()
    val isShowProgress: LiveData<Boolean>
        get() = _isShowProgress

    private val _isShowFailMessage = MutableLiveData<Boolean>()
    val isShowFailMessage: LiveData<Boolean>
        get() = _isShowFailMessage

    val email = MutableLiveData<String>()

    val isValidEmail = email.map {
        it?.isNotEmpty() ?: false
    }

    val password = MutableLiveData<String>()

    val isValidPassword = password.map {
        it?.isNotEmpty() ?: false
    }

    private fun getProfile(user: FirebaseUser) {
        viewModelScope.launch {
            user.uid.let {
                getProfileUseCase(it)?.let {
//                    _navigateToHome.value = true
                    setEffect(SignInEffect.NavigateToHome)
                    _isShowProgress.value = false
                } ?: run {
//                    _navigateToSignUp.value = true
                    setEffect(SignInEffect.NavigateToSignUp)
                    _isShowProgress.value = false
                }
            }
        }
    }

    fun signInWithGoogle(token: String) {
        viewModelScope.launch {
            _progressMessage.value = "로그인 중입니다."
            _isShowProgress.value = true
            val result = signInWithGoogleUseCase(token)
            result?.user?.let { getProfile(it) }
        }
    }

    fun signInWithEmailAndPassword() {
        viewModelScope.launch {
            _progressMessage.value = "로그인 중입니다."
            _isShowProgress.value = true

            signInWithEmailAndPasswordUseCase(
                SignInWithEmailAndPasswordUseCase.Params(
                    email.value,
                    password.value,
                ),
            )
                .onSuccess {
                    // Login Event -> Success -> effect(Navigate to Home)
                    setEffect(SignInEffect.NavigateToHome)
                }
                .onFailure {
                    // Loign Event -> Fail -> effect(Show Fail Message)
                    _progressMessage.value = "로그인에 실패했습니다."
                }
        }
    }
}
