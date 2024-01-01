package com.seallook.androidx.ui.splash

import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.GetProfileUseCase
import com.seallook.androidx.domain.usecase.usertype.UpdateUserTypeUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val updateUserTypeUseCase: UpdateUserTypeUseCase,
) : BaseViewModel<SplashEffect>() {
    init {
        viewModelScope.launch {
            getCurrentUserUseCase()?.let {
                getProfileUseCase(it.uid).let {
                    updateUserTypeUseCase(
                        UpdateUserTypeUseCase.Params(
                            it?.email,
                            it?.userType,
                        ),
                    )
                        .onSuccess {
                            setEffect(SplashEffect.NavigateToMain)
                        }
                        .onFailure {
                            setEffect(SplashEffect.NavigateToAuth)
                        }
                }
            } ?: run {
                setEffect(SplashEffect.NavigateToAuth)
            }
        }
    }
}
