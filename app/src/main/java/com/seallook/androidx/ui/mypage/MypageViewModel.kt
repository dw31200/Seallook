package com.seallook.androidx.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.usertype.GetUserTypeUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.base.Effect
import com.seallook.androidx.ui.model.UserTypeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(
    getUserTypeUseCase: GetUserTypeUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : BaseViewModel<Effect>() {
    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    val userType: LiveData<UserTypeUiModel?> = getUserTypeUseCase().map {
        it?.let {
            UserTypeUiModel(it)
        }
    }.asLiveData()

    init {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase()
        }
    }
}
