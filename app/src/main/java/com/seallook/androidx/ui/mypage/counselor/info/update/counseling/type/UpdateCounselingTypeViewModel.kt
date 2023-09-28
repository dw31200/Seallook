package com.seallook.androidx.ui.mypage.counselor.info.update.counseling.type

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.model.CounselingTypeModel
import com.seallook.androidx.domain.usecase.DeleteCounselingTypeUseCase
import com.seallook.androidx.domain.usecase.GetCounselingTypeUseCase
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.SetCounselingTypeUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateCounselingTypeViewModel @Inject constructor(
    private val getCounselingTypeUseCase: GetCounselingTypeUseCase,
    private val setCounselingTypeUseCase: SetCounselingTypeUseCase,
    private val deleteCounselingTypeUseCase: DeleteCounselingTypeUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : BaseViewModel() {
    val counselingType: StateFlow<List<CounselingTypeModel>> = getCounselingTypeUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            mutableListOf(),
        )
    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    init {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase()
        }
    }

    fun setCounselingType(counselingTypeModel: CounselingTypeModel) {
        viewModelScope.launch {
            setCounselingTypeUseCase(counselingTypeModel)
        }
    }

    fun deleteCounselingType(counselingTypeId: Int) {
        viewModelScope.launch {
            deleteCounselingTypeUseCase(counselingTypeId)
        }
    }
}
