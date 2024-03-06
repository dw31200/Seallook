package com.seallook.androidx.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.usecase.counselorinfo.basic.GetCounselorInfoListUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.RefreshCounselorInfoListUseCase
import com.seallook.androidx.domain.usecase.kakao.GetKakaoSearchListUseCase
import com.seallook.androidx.domain.usecase.location.GetCurrentLocationUseCase
import com.seallook.androidx.domain.usecase.usertype.GetUserTypeUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.base.Effect
import com.seallook.androidx.ui.model.CounselorInfoUiModel
import com.seallook.androidx.ui.model.KakaoSearchUiModel
import com.seallook.androidx.ui.model.UserTypeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val refreshCounselorInfoListUseCase: RefreshCounselorInfoListUseCase,
    getCounselorInfoListUseCase: GetCounselorInfoListUseCase,
    getUserTypeUseCase: GetUserTypeUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val getKakaoSearchListUseCase: GetKakaoSearchListUseCase,
) : BaseViewModel<Effect>() {
    val userType: LiveData<UserTypeUiModel?> = getUserTypeUseCase().map {
        it?.let {
            UserTypeUiModel(it)
        }
    }.asLiveData()

    val counselorInfoList: LiveData<List<CounselorInfoUiModel>> =
        getCounselorInfoListUseCase(null)
            .map {
                it.map {
                    CounselorInfoUiModel(it)
                }
            }.asLiveData()

    private val _officeList = MutableLiveData<List<KakaoSearchUiModel>>()
    val officeList: LiveData<List<KakaoSearchUiModel>>
        get() = _officeList

    init {
        viewModelScope.launch {
            refreshCounselorInfoListUseCase()
        }
    }

    fun getLocation() {
        viewModelScope.launch {
            val result = getCurrentLocationUseCase()
            if (result.location != null) {
                _officeList.value = getKakaoSearchListUseCase(
                    "상담센터",
                    result.location.longitude.toString(),
                    result.location.latitude.toString(),
                ).map {
                    KakaoSearchUiModel(it)
                }
            }
            Timber.d("latitude: ${result.location?.latitude}, longitude: ${result.location?.longitude}")
        }
    }
}
