package com.seallook.androidx.ui.mypage.setting

import com.seallook.androidx.domain.usecase.SignOutUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase,
) : BaseViewModel() {
    fun signOut() {
        signOutUseCase()
    }
}
