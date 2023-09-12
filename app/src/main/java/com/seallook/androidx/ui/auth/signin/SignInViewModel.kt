package com.seallook.androidx.ui.auth.signin

import androidx.lifecycle.asLiveData
import com.seallook.androidx.domain.usecase.GetProfileUseCase
import com.seallook.androidx.domain.usecase.SignInWithGoogleUseCase
import com.seallook.androidx.domain.usecase.SignOutUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val signOutUseCase: SignOutUseCase,
) : BaseViewModel() {
    val profile = getProfileUseCase().asLiveData()

    suspend fun signInWithGoogle(token: String) = signInWithGoogleUseCase(token)

    fun signOut() {
        signOutUseCase()
    }
}
