package com.seallook.androidx.ui.auth.signup

import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.model.ProfileEntity
import com.seallook.androidx.domain.usecase.GetProfileUseCase
import com.seallook.androidx.domain.usecase.SignUpUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val getProfileUseCase: GetProfileUseCase,
) : BaseViewModel() {
    val profile = getProfileUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null,
        )

    suspend fun signUp(profile: ProfileEntity, password: String? = null) {
        signUpUseCase(profile, password)
    }
}
