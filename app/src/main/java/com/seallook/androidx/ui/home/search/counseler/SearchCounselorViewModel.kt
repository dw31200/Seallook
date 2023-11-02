package com.seallook.androidx.ui.home.search.counseler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.base.Effect
import com.seallook.androidx.domain.usecase.counselorinfo.basic.GetCounselorInfoListUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.CounselorInfoUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCounselorViewModel @Inject constructor(
    private val getCounselorInfoListUseCase: GetCounselorInfoListUseCase,
) : BaseViewModel<Effect>() {
    private val _counselorInfoList = MutableLiveData<List<CounselorInfoUiModel>>()
    val counselorInfoList: LiveData<List<CounselorInfoUiModel>>
        get() = _counselorInfoList

    init {
        viewModelScope.launch {
            _counselorInfoList.value = getCounselorInfoListUseCase().map {
                CounselorInfoUiModel(it)
            }
        }
    }
}
