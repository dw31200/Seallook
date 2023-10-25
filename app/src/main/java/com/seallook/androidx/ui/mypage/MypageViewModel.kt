package com.seallook.androidx.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.GetUserTypeUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.ProfileUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(
    private val getUserTypeUseCase: GetUserTypeUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : BaseViewModel() {
    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser
    private val _userType = MutableLiveData<ProfileUiModel?>()
    val userType: LiveData<ProfileUiModel?>
        get() = _userType

    init {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase()
            _userType.value = currentUser.value?.uid?.let {
                getUserTypeUseCase(it)?.let {
                    ProfileUiModel(it)
                }
            }
        }
    }
}
