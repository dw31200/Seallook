package com.seallook.androidx.ui.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.usecase.GetProfileUseCase
import com.seallook.androidx.domain.usecase.SignInWithEmailAndPasswordUseCase
import com.seallook.androidx.domain.usecase.SignInWithGoogleUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

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

    private val _isShowFailMessage = MutableLiveData<String>()
    val isShowFailMessage: LiveData<String>
        get() = _isShowFailMessage

    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    private fun getProfile(uid: String) {
        viewModelScope.launch {
            getProfileUseCase(uid)?.let {
                setEffect(SignInEffect.NavigateToHome)
            } ?: run {
                setEffect(SignInEffect.NavigateToSignUp)
            }
        }
    }

    fun signInWithGoogle(token: String) {
        viewModelScope.launch {
            _progressMessage.value = "로그인 중입니다."
            _isShowProgress.value = true
            signInWithGoogleUseCase(token)
                .onSuccess {
                    it.user?.uid?.let { uid ->
                        getProfile(uid)
                        _isShowProgress.value = false
                    }
                }
                .onFailure {
                    _isShowProgress.value = false
                }
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
                    _isShowProgress.value = false
                    setEffect(SignInEffect.NavigateToHome)
                }
                .onFailure {
                    Timber.d("$it")
                    _isShowFailMessage.value = "로그인에 실패했습니다."
                    _isShowProgress.value = false
                }
        }
    }
}
