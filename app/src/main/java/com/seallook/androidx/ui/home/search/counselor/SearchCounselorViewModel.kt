package com.seallook.androidx.ui.home.search.counselor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.base.Effect
import com.seallook.androidx.domain.usecase.counselorinfo.basic.GetCounselorInfoListUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.RefreshCounselorInfoListUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.CounselorInfoUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCounselorViewModel @Inject constructor(
    private val refreshCounselorInfoListUseCase: RefreshCounselorInfoListUseCase,
    getCounselorInfoListUseCase: GetCounselorInfoListUseCase,
) : BaseViewModel<Effect>() {
    val searchQuery = MutableLiveData<String?>()
    val counselorInfoList: LiveData<List<CounselorInfoUiModel>> =
        searchQuery.asFlow()
            .flatMapLatest {
                getCounselorInfoListUseCase(it)
                    .map {
                        it.map {
                            CounselorInfoUiModel(it)
                        }
                    }
            }.asLiveData()

    init {
        viewModelScope.launch {
            refreshCounselorInfoListUseCase()
        }
    }
}
