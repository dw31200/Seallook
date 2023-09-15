package com.seallook.androidx.ui.auth.signup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.model.ProfileEntity
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.GetProfileUseCase
import com.seallook.androidx.domain.usecase.SignUpUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {
    val profile = getProfileUseCase().asLiveData()
    val signUpType = savedStateHandle.getStateFlow("selectSignUpType", 0)
    fun getCurrentUser(): FirebaseUser? {
        return getCurrentUserUseCase()
    }

    suspend fun signUp(profile: ProfileEntity, password: String? = null): Exception? {
        return signUpUseCase(profile, password)
    }
}
