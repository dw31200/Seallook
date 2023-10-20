package com.seallook.androidx.ui.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.GetProfileUseCase
import com.seallook.androidx.domain.usecase.SignInWithEmailAndPasswordUseCase
import com.seallook.androidx.domain.usecase.SignInWithGoogleUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.ProfileUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : BaseViewModel() {
    private val _profile = MutableLiveData<ProfileUiModel?>()
    val profile: LiveData<ProfileUiModel?>
        get() = _profile
    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser
    private val _beginSignInResult = MutableLiveData<BeginSignInResult?>()
    val beginSignInResult: LiveData<BeginSignInResult?>
        get() = _beginSignInResult
    private val _signInWithGoogleResult = MutableLiveData<AuthResult?>()
    val signInWithGoogleResult: LiveData<AuthResult?>
        get() = _signInWithGoogleResult
    private val _signInWithEmailResult = MutableLiveData<AuthResult?>()
    val signInWithEmailResult: LiveData<AuthResult?>
        get() = _signInWithEmailResult

    fun getCurrentUser() {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase()
        }
    }

    fun getProfile(user: FirebaseUser) {
        viewModelScope.launch {
            _profile.value = getProfileUseCase(user)?.let { ProfileUiModel(it) }
        }
    }

    fun signInWithGoogle(token: String) {
        viewModelScope.launch {
            _signInWithGoogleResult.value = signInWithGoogleUseCase(token)
        }
    }

    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            try {
                _signInWithEmailResult.value = signInWithEmailAndPasswordUseCase(email, password)
            } catch (e: Exception) {
                _signInWithEmailResult.value = null
            }
        }
    }
}
