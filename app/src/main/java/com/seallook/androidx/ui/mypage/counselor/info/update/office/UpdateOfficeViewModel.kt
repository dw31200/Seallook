package com.seallook.androidx.ui.mypage.counselor.info.update.office

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.model.NaverSearchModel
import com.seallook.androidx.domain.model.OfficeInfoModel
import com.seallook.androidx.domain.usecase.GetNaverSearchUseCase
import com.seallook.androidx.domain.usecase.SetOfficeInfoUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateOfficeViewModel @Inject constructor(
    private val getNaverSearchUseCase: GetNaverSearchUseCase,
    private val setOfficeInfoUseCase: SetOfficeInfoUseCase,
) : BaseViewModel() {
    private val type = "local.json"
    private val _naverSearchModel = MutableLiveData<List<NaverSearchModel>?>()
    val naverSearchModel: LiveData<List<NaverSearchModel>?>
        get() = _naverSearchModel

    fun searchOnClick(query: String) {
        viewModelScope.launch {
            _naverSearchModel.value = getNaverSearchUseCase(type, query)
        }
    }

    fun setOfficeInfo(id: Int, info: OfficeInfoModel) {
        viewModelScope.launch {
            setOfficeInfoUseCase(id, info)
        }
    }
}
