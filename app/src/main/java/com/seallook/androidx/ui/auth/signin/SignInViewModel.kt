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
    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean>
        get() = _navigateToHome

    private val _navigateToSignUp = MutableLiveData<Boolean>()
    val navigateToSignUp: LiveData<Boolean>
        get() = _navigateToSignUp

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

    val password = MutableLiveData<String>()

    private fun getProfile(uid: String) {
        viewModelScope.launch {
            getProfileUseCase(uid)?.let {
                _navigateToHome.value = true
                _isShowProgress.value = false
            } ?: run {
                _navigateToSignUp.value = true
                _isShowProgress.value = false
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
                    }
                }
                .onFailure {
                    Unit
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
                    _navigateToHome.value = true
                }
                .onFailure {
                    Timber.d("$it")
                    _isShowFailMessage.value = true
                    _isShowProgress.value = false
                }
        }
    }
}
