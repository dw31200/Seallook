package com.seallook.androidx.ui.mypage.counselor.info.update.office

import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.model.NaverSearchModel
import com.seallook.androidx.domain.usecase.GetNaverSearchUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UpdateOfficeViewModel @Inject constructor(
    private val getNaverSearchUseCase: GetNaverSearchUseCase,
) : BaseViewModel() {
    private val type = "local.json"
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    val naverSearchModel: StateFlow<List<NaverSearchModel>> = getNaverSearchUseCase(type, query.value)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            mutableListOf(),
        )
}
