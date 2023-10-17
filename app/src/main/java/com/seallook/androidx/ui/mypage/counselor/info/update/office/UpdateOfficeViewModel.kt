package com.seallook.androidx.ui.mypage.counselor.info.update.office

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.usecase.GetOfficeInfoListUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.office.SetOfficeInfoUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.OfficeInfoUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateOfficeViewModel @Inject constructor(
    private val getOfficeInfoListUseCase: GetOfficeInfoListUseCase,
    private val setOfficeInfoUseCase: SetOfficeInfoUseCase,
) : BaseViewModel() {
    private val type = "local.json"
    private val _officeInfoList = MutableLiveData<List<OfficeInfoUiModel>>()
    val officeInfoList: LiveData<List<OfficeInfoUiModel>>
        get() = _officeInfoList

    fun searchOnClick(query: String) {
        viewModelScope.launch {
            _officeInfoList.value = getOfficeInfoListUseCase(type, query)?.let {
                it.map {
                    OfficeInfoUiModel(it)
                }
            }
        }
    }

    fun setOfficeInfo(id: Int, info: OfficeInfoUiModel) {
        viewModelScope.launch {
            setOfficeInfoUseCase(id, info.toDomainModel())
        }
    }
}
