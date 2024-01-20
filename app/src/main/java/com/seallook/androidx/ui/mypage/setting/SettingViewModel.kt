package com.seallook.androidx.ui.mypage.setting

import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.usecase.SignOutUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.base.Effect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
) : BaseViewModel<Effect>() {
    fun signOut() {
        viewModelScope.launch {
            signOutUseCase()
        }
    }
}
