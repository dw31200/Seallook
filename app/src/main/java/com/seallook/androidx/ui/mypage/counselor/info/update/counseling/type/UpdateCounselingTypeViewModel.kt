package com.seallook.androidx.ui.mypage.counselor.info.update.counseling.type

import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.usecase.GetCounselingTypeUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UpdateCounselingTypeViewModel @Inject constructor(
    private val getCounselingTypeUseCase: GetCounselingTypeUseCase,
) : BaseViewModel() {
    val counselingType = getCounselingTypeUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null,
        )
}
