package com.seallook.androidx.ui.mypage.counselor.info.update.counseling.type

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.seallook.androidx.base.Effect
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.CombineCounselingTypeUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.DeleteCounselingTypeUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.GetCounselingTypeLocalUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.SetCounselingTypeUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.CounselingTypeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.grpc.internal.JsonUtil.getList
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateCounselingTypeViewModel @Inject constructor(
    private val getCounselingTypeLocalUseCase: GetCounselingTypeLocalUseCase,
    private val setCounselingTypeUseCase: SetCounselingTypeUseCase,
    private val deleteCounselingTypeUseCase: DeleteCounselingTypeUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val combineCounselingTypeUseCase: CombineCounselingTypeUseCase,
) : BaseViewModel<Effect>(), DeleteCounselingType {
    private val _counselingType = MutableLiveData<List<CounselingTypeUiModel>>()
    val counselingType: LiveData<List<CounselingTypeUiModel>>
        get() = _counselingType
    val title = MutableLiveData<String>()
    val clientCount = MutableLiveData<String>()
    val time = MutableLiveData<String>()
    val price = MutableLiveData<String>()

    init {
        initList()
    }

    fun setItem() {
        viewModelScope.launch {
            if (title.value == "" || clientCount.value == "" || time.value == "" || price.value == "") {
                return@launch
            } else {
                if (counselingType.value?.isEmpty() == true) {
                    setCounselingTypeUseCase(
                        CounselingTypeUiModel(
                            0,
                            title.value ?: return@launch,
                            clientCount.value?.toInt() ?: return@launch,
                            time.value?.toInt() ?: return@launch,
                            price.value?.toInt() ?: return@launch,
                        )
                            .toDomainModel(),
                    )
                } else {
                    setCounselingTypeUseCase(
                        CounselingTypeUiModel(
                            (counselingType.value?.last()?.id ?: return@launch) + 1,
                            title.value ?: return@launch,
                            clientCount.value?.toInt() ?: return@launch,
                            time.value?.toInt() ?: return@launch,
                            price.value?.toInt() ?: return@launch,
                        )
                            .toDomainModel(),
                    )
                }
            }
            getList()
        }
    }

    override fun deleteItem(counselingTypeId: Int) {
        viewModelScope.launch {
            deleteCounselingTypeUseCase(counselingTypeId)
            getList()
        }
    }

    private fun getList() {
        viewModelScope.launch {
            _counselingType.value = getCounselingTypeLocalUseCase()
                .map {
                    CounselingTypeUiModel(it)
                }
        }
    }

    private fun initList() {
        viewModelScope.launch {
            getCurrentUserUseCase()?.uid.let {
                if (it != null) {
                    combineCounselingTypeUseCase(it)
                        .onEach { list ->
                            _counselingType.value = list.map { item ->
                                CounselingTypeUiModel(item)
                            }
                        }.launchIn(this)
                }
            }
        }
    }
}
