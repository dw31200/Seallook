package com.seallook.androidx.ui.mypage.counselor.info.update.counseling.type

import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.model.CounselingTypeModel
import com.seallook.androidx.domain.usecase.GetCounselingTypeUseCase
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
) : BaseViewModel() {
    val counselingType: StateFlow<List<CounselingTypeModel>> = getCounselingTypeUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            mutableListOf(),
        )

    fun setCounselingType(counselingTypeModel: CounselingTypeModel) {
        viewModelScope.launch {
            setCounselingTypeUseCase(counselingTypeModel)
        }
    }
}
