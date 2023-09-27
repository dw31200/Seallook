package com.seallook.androidx.ui.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.model.ProfileEntity
import com.seallook.androidx.domain.usecase.GetBeginSignInResultUseCase
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.GetProfileUseCase
import com.seallook.androidx.domain.usecase.SignInWithEmailAndPasswordUseCase
import com.seallook.androidx.domain.usecase.SignInWithGoogleUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val getBeginSignInResultUseCase: GetBeginSignInResultUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : BaseViewModel() {
    private val _profile = MutableLiveData<ProfileEntity?>()
    val profile: LiveData<ProfileEntity?>
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
            _currentUser.value = getCurrentUserUseCase.getCurrentUser()
        }
    }

    fun getProfile(user: FirebaseUser) {
        viewModelScope.launch {
            _profile.value = getProfileUseCase(user)
        }
    }

    fun getBeginSignInResult() {
        viewModelScope.launch {
            _beginSignInResult.value = getBeginSignInResultUseCase()
        }
    }

    fun signInWithGoogle(token: String) {
        viewModelScope.launch {
            _signInWithGoogleResult.value = signInWithGoogleUseCase(token)
        }
    }

    fun signInWithEmailAndPassword(email: String, password: String) {
        viewModelScope.launch {
            _signInWithEmailResult.value = signInWithEmailAndPasswordUseCase(email, password)
        }
    }
}
