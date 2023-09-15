package com.seallook.androidx.ui.auth.signup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.model.ProfileEntity
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.GetProfileUseCase
import com.seallook.androidx.domain.usecase.SetProfileUseCase
import com.seallook.androidx.domain.usecase.SignOutUseCase
import com.seallook.androidx.domain.usecase.SignUpUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val setProfileUseCase: SetProfileUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {
    val profile = getProfileUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null,
        )
    val signUpType = savedStateHandle.getStateFlow("selectSignUpType", 0)
    val currentUser: StateFlow<FirebaseUser?> = getCurrentUserUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null,
        )

    suspend fun signUp(profile: ProfileEntity, password: String? = null): Exception? {
        return signUpUseCase(profile, password)
    }

    suspend fun setProfile(profile: ProfileEntity) {
        setProfileUseCase(profile.toProfile())
    }

    fun signOut() {
        signOutUseCase()
    }
}
