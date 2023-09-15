package com.seallook.androidx.ui.auth.signin

import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthResult
import com.seallook.androidx.domain.usecase.GetBeginSignInResultUseCase
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.GetProfileUseCase
import com.seallook.androidx.domain.usecase.SignInWithGoogleUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val getBeginSignInResultUseCase: GetBeginSignInResultUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : BaseViewModel() {
    val profile = getProfileUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null,
        )

    suspend fun getBeginSignInResult(): BeginSignInResult {
        return getBeginSignInResultUseCase()
    }

    suspend fun signInWithGoogle(token: String): AuthResult? = signInWithGoogleUseCase(token)
}
