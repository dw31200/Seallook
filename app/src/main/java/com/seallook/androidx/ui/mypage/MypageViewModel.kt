package com.seallook.androidx.ui.mypage

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.model.UserTypeEntity
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.GetUserTypeUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(
    private val getUserTypeUseCase: GetUserTypeUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : BaseViewModel() {
    val currentUser: StateFlow<FirebaseUser?> = getCurrentUserUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null,
        )
    val userType: StateFlow<UserTypeEntity> =
        currentUser
            .flatMapLatest { currentUser ->
                getUserTypeUseCase(currentUser)
            }.stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                UserTypeEntity(0),
            )
}
