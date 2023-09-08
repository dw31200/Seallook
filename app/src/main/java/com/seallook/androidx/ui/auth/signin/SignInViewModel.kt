package com.seallook.androidx.ui.auth.signin

import com.seallook.androidx.domain.SignInWithGoogleUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
) : BaseViewModel() {
    suspend fun signInWithGoogle(token: String) = signInWithGoogleUseCase(token)
}
