package com.seallook.androidx.ui.mypage.counselor.info.update.counseling.type

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.DeleteCounselingTypeUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.GetCounselingTypeUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.InitCounselingTypeUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.SetCounselingTypeUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.CounselingTypeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateCounselingTypeViewModel @Inject constructor(
    private val getCounselingTypeUseCase: GetCounselingTypeUseCase,
    private val setCounselingTypeUseCase: SetCounselingTypeUseCase,
    private val deleteCounselingTypeUseCase: DeleteCounselingTypeUseCase,
    private val initCounselingTypeUseCase: InitCounselingTypeUseCase,
) : BaseViewModel() {
    private val _counselingType = MutableLiveData<List<CounselingTypeUiModel>>()
    val counselingType: LiveData<List<CounselingTypeUiModel>>
        get() = _counselingType

    init {
        viewModelScope.launch {
            _counselingType.value = initCounselingTypeUseCase().map {
                CounselingTypeUiModel(it)
            }
        }
    }

    fun setCounselingType(counselingTypeUiModel: CounselingTypeUiModel) {
        viewModelScope.launch {
            setCounselingTypeUseCase(counselingTypeUiModel.toDomainModel())
            getCounselingType()
        }
    }

    fun deleteCounselingType(counselingTypeId: Int) {
        viewModelScope.launch {
            deleteCounselingTypeUseCase(counselingTypeId)
            getCounselingType()
        }
    }

    private fun getCounselingType() {
        viewModelScope.launch {
            _counselingType.value = getCounselingTypeUseCase().map {
                CounselingTypeUiModel(it)
            }
        }
    }
}
