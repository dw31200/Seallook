package com.seallook.androidx.ui.mypage.counselor.info.update.office

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.office.SetCounselorOfficeIdUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.office.SetOfficeInfoUseCase
import com.seallook.androidx.domain.usecase.kakao.GetKakaoSearchListUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.OfficeInfoUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateOfficeViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val setOfficeInfoUseCase: SetOfficeInfoUseCase,
    private val getKakaoSearchListUseCase: GetKakaoSearchListUseCase,
    private val setCounselorOfficeIdUseCase: SetCounselorOfficeIdUseCase,
) : BaseViewModel<UpdateOfficeEffect>() {
    private val _officeInfoList = MutableLiveData<List<OfficeInfoUiModel>>()
    val officeInfoList: LiveData<List<OfficeInfoUiModel>>
        get() = _officeInfoList

    fun searchOnClick(query: String) {
        viewModelScope.launch {
            _officeInfoList.value = getKakaoSearchListUseCase(
                query,
            ).map {
                OfficeInfoUiModel(it)
            }
        }
    }

    fun setOfficeInfo(info: OfficeInfoUiModel) {
        viewModelScope.launch {
            setOfficeInfoUseCase(info.toDomainModel())
                .onSuccess {
                    setEffect(UpdateOfficeEffect.SuccessUpdateOfficeInfo)
                    setOfficeId(info)
                }
        }
    }

    private fun setOfficeId(info: OfficeInfoUiModel) {
        viewModelScope.launch {
            setCounselorOfficeIdUseCase(
                SetCounselorOfficeIdUseCase.Params(
                    getCurrentUserUseCase()?.email,
                    info.id,
                ),
            ).onSuccess {
                setEffect(UpdateOfficeEffect.SuccessUpdateOfficeId)
            }
        }
    }
}
