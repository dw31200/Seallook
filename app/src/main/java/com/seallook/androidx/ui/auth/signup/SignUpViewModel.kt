package com.seallook.androidx.ui.auth.signup

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.model.ProfileEntity
import com.seallook.androidx.domain.model.UserTypeEntity
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.GetProfileSnapshotUseCase
import com.seallook.androidx.domain.usecase.GetProfileUseCase
import com.seallook.androidx.domain.usecase.GetTaskProfileUseCase
import com.seallook.androidx.domain.usecase.SetProfileUseCase
import com.seallook.androidx.domain.usecase.SetUserTypeUseCase
import com.seallook.androidx.domain.usecase.SignOutUseCase
import com.seallook.androidx.domain.usecase.SignUpUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getTaskProfileUseCase: GetTaskProfileUseCase,
    private val getProfileSnapshotUseCase: GetProfileSnapshotUseCase,
    private val setProfileUseCase: SetProfileUseCase,
    private val setUserTypeUseCase: SetUserTypeUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {
    val profile = getProfileUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null,
        )
    val taskProfile = getTaskProfileUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null,
        )
    val currentUser: StateFlow<FirebaseUser?> = getCurrentUserUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null,
        )
    val profileSnapshot: StateFlow<ProfileEntity?> =
        currentUser
            .flatMapLatest { currentUser ->
                getProfileSnapshotUseCase(currentUser)
            }.stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                null,
            )
    val signUpType = savedStateHandle.getStateFlow("selectSignUpType", 0)
    lateinit var result: StateFlow<AuthResult?>

    fun signUp(profile: ProfileEntity, password: String? = null): Exception? {
        return try {
            result = signUpUseCase(profile, password)
                .stateIn(
                    viewModelScope,
                    SharingStarted.WhileSubscribed(),
                    null,
                )
            null
        } catch (e: Exception) {
            e
        }
    }

    fun setProfile(profile: ProfileEntity) {
        viewModelScope.launch {
            setProfileUseCase(profile.toProfile())
        }
    }

    fun setUserType() {
        viewModelScope.launch {
            setUserTypeUseCase(UserTypeEntity(signUpType.value))
        }
    }

    fun signOut() {
        signOutUseCase()
    }
}
