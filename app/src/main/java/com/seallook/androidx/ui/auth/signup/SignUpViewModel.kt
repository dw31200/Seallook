package com.seallook.androidx.ui.auth.signup

import com.seallook.androidx.domain.SignUpUseCase
import com.seallook.androidx.domain.model.ProfileEntity
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
) : BaseViewModel() {
    suspend fun signUp(profile: ProfileEntity, password: String? = null) {
        signUpUseCase(profile, password)
    }
}
