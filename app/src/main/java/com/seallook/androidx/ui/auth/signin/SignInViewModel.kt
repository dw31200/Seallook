package com.seallook.androidx.ui.auth.signin

import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.GetProfileUseCase
import com.seallook.androidx.domain.usecase.SignInWithEmailAndPasswordUseCase
import com.seallook.androidx.domain.usecase.SignInWithGoogleUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.ProfileUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : BaseViewModel() {
    fun getProfile() {
        viewModelScope.launch {
            getCurrentUserUseCase()?.uid?.let {
                getProfileUseCase(it)?.let { ProfileUiModel(it) }
            }
        }
    }

    fun signInWithGoogle(token: String) {
        viewModelScope.launch {
            signInWithGoogleUseCase(token)
        }
    }

    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            try {
                signInWithEmailAndPasswordUseCase(email, password)
            } catch (e: Exception) {
                Timber.d("$e")
            }
        }
    }
}
