package com.seallook.androidx.ui.home

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.base.Effect
import com.seallook.androidx.domain.usecase.counselorinfo.basic.GetCounselorInfoListUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.RefreshCounselorInfoListUseCase
import com.seallook.androidx.domain.usecase.kakao.GetKakaoSearchListUseCase
import com.seallook.androidx.domain.usecase.usertype.GetUserTypeUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.CounselorInfoUiModel
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
    private val getKakaoSearchListUseCase: GetKakaoSearchListUseCase,
) : BaseViewModel<Effect>() {
    val userType: LiveData<UserTypeUiModel?> = getUserTypeUseCase().map {
        it?.let {
            UserTypeUiModel(it)
        }
    }.asLiveData()

    val counselorInfoList: LiveData<List<CounselorInfoUiModel>> =
        getCounselorInfoListUseCase()
            .map {
                it.map {
                    CounselorInfoUiModel(it)
                }
            }.asLiveData()

    init {
        viewModelScope.launch {
            refreshCounselorInfoListUseCase()
            val list = getKakaoSearchListUseCase("상담센터")
            Timber.d("$list")
        }
    }

    fun getLocation(location: Location?) {
        Timber.d("${location?.latitude}, ${location?.longitude}")
    }
}
