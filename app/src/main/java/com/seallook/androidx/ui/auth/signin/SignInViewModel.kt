package com.seallook.androidx.ui.auth.signin

import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.model.ProfileEntity
import com.seallook.androidx.domain.usecase.GetBeginSignInResultUseCase
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.GetProfileSnapshotUseCase
import com.seallook.androidx.domain.usecase.GetProfileUseCase
import com.seallook.androidx.domain.usecase.GetTaskProfileUseCase
import com.seallook.androidx.domain.usecase.SignInWithEmailAndPasswordUseCase
import com.seallook.androidx.domain.usecase.SignInWithGoogleUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val getTaskProfileUseCase: GetTaskProfileUseCase,
    private val getProfileSnapshotUseCase: GetProfileSnapshotUseCase,
    private val getBeginSignInResultUseCase: GetBeginSignInResultUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : BaseViewModel() {
    val profile = getTaskProfileUseCase()
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

//    val profileSnapshot = getProfileSnapshotUseCase(currentUser.value)
//        .stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(),
//            null,
//        )

    suspend fun getBeginSignInResult(): BeginSignInResult {
        return getBeginSignInResultUseCase()
    }

    suspend fun signInWithGoogle(token: String): AuthResult? = signInWithGoogleUseCase(token)

    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult? =
        signInWithEmailAndPasswordUseCase(email, password)
}
