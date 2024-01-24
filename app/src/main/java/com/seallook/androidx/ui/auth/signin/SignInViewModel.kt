package com.seallook.androidx.ui.auth.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.usecase.GetProfileUseCase
import com.seallook.androidx.domain.usecase.SignInWithEmailAndPasswordUseCase
import com.seallook.androidx.domain.usecase.SignInWithGoogleUseCase
import com.seallook.androidx.domain.usecase.usertype.UpdateUserTypeUseCase
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
    private val updateUserTypeUseCase: UpdateUserTypeUseCase,
) : BaseViewModel<SignInEffect>() {
    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    private fun getProfile(uid: String) {
        viewModelScope.launch {
            getProfileUseCase(uid)?.let {
                updateUserTypeUseCase(
                    UpdateUserTypeUseCase.Params(
                        it.email,
                        it.userType,
                    ),
                )
                    .onSuccess {
                        setEffect(SignInEffect.SuccessSignIn)
                    }
                    .onFailure {
                        setEffect(SignInEffect.FailureSignIn)
                    }
            } ?: run {
                setEffect(SignInEffect.NavigateToSignUp)
            }
        }
    }

    fun signInWithGoogle(token: String) {
        setEffect(SignInEffect.SignInWithGoogle)
        viewModelScope.launch {
            signInWithGoogleUseCase(token)
                .onSuccess {
                    setEffect(SignInEffect.SuccessSignInWithGoogle)
                    it.user?.uid?.let { uid ->
                        getProfile(uid)
                    }
                }
                .onFailure {
                    setEffect(SignInEffect.FailureSignInWithGoogle)
                }
        }
    }

    fun signInWithEmailAndPassword() {
        setEffect(SignInEffect.SignInWithEmailAndPassword)
        viewModelScope.launch {
            signInWithEmailAndPasswordUseCase(
                SignInWithEmailAndPasswordUseCase.Params(
                    email.value,
                    password.value,
                ),
            )
                .onSuccess {
                    setEffect(SignInEffect.SuccessSignInWithEmailAndPassword)
                    it.user?.uid?.let { uid ->
                        getProfile(uid)
                    }
                }
                .onFailure {
                    setEffect(SignInEffect.FailureSignInWithEmailAndPassword)
                    Timber.d("$it")
                }
        }
    }
}
