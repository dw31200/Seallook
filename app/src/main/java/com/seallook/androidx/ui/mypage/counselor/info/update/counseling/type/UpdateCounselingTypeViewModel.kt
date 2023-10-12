package com.seallook.androidx.ui.mypage.counselor.info.update.counseling.type

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.domain.model.CounselingTypeModel
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.DeleteCounselingTypeUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.GetCounselingTypeUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.InitCounselingTypeUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.SetCounselingTypeUseCase
import com.seallook.androidx.ui.base.BaseViewModel
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
    private val _counselingType = MutableLiveData<List<CounselingTypeModel>?>()
    val counselingType: LiveData<List<CounselingTypeModel>?>
        get() = _counselingType

    init {
        viewModelScope.launch {
            _counselingType.value = initCounselingTypeUseCase()
        }
    }

    fun setCounselingType(counselingTypeModel: CounselingTypeModel) {
        viewModelScope.launch {
            setCounselingTypeUseCase(counselingTypeModel)
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
            _counselingType.value = getCounselingTypeUseCase()
        }
    }
}
