package com.seallook.androidx.ui.mypage.counselor.info.update.basic

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.GetCounselorInfoUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.GetDownloadUrlUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.SetCounselorInfoUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.UploadFileUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.GetCounselingTypeLocalUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.UpdateCounselingTypeUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.office.GetAllOfficeInfoUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.office.GetOfficeInfoUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.office.UpdateOfficeInfoUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.CounselorInfoUiModel
import com.seallook.androidx.ui.model.OfficeInfoUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateCounselorBasicInfoViewModel @Inject constructor(
    private val getDownloadUrlUseCase: GetDownloadUrlUseCase,
    private val uploadFileUseCase: UploadFileUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val updateCounselingTypeUseCase: UpdateCounselingTypeUseCase,
    private val getCounselingTypeLocalUseCase: GetCounselingTypeLocalUseCase,
    private val getCounselorInfoUseCase: GetCounselorInfoUseCase,
    private val setCounselorInfoUseCase: SetCounselorInfoUseCase,
    private val getOfficeInfoUseCase: GetOfficeInfoUseCase,
    private val getAllOfficeInfoUseCase: GetAllOfficeInfoUseCase,
    private val updateOfficeInfoUseCase: UpdateOfficeInfoUseCase,
) : BaseViewModel<UpdateCounselorBasicInfoEffect>() {
    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    private val _counselorInfo = MutableLiveData<CounselorInfoUiModel?>()
    val counselorInfo: LiveData<CounselorInfoUiModel?>
        get() = _counselorInfo

    private val _officeInfo = MutableLiveData<OfficeInfoUiModel?>()
    val officeInfo: LiveData<OfficeInfoUiModel?>
        get() = _officeInfo

    private val _progressMessage = MutableLiveData<String>()
    val progressMessage: LiveData<String>
        get() = _progressMessage

    private val _isShowProgress = MutableLiveData<Boolean>()
    val isShowProgress: LiveData<Boolean>
        get() = _isShowProgress

    private val _isShowFailMessage = MutableLiveData<String>()
    val isShowFailMessage: LiveData<String>
        get() = _isShowFailMessage

    init {
        viewModelScope.launch {
            _progressMessage.value = "상담사 정보를 업로드 중입니다."
            _currentUser.value = getCurrentUserUseCase()
            _counselorInfo.value =
                currentUser.value?.uid?.let {
                    getCounselorInfoUseCase(it)?.let {
                        CounselorInfoUiModel(it)
                    }
                }
            _officeInfo.value =
//                TODO sdw312 임시 id
                getOfficeInfoUseCase("0")?.let {
                    OfficeInfoUiModel(it)
                }
        }
    }

    fun uploadFile(path: String, fileName: String, uri: Uri, name: String?, pr: String?) {
        viewModelScope.launch {
            uploadFileUseCase(path, fileName, uri)
                .onSuccess {
                    getDownloadUrl(path, fileName, name, pr)
                }
        }
    }

    private fun getDownloadUrl(path: String, fileName: String, name: String?, pr: String?) {
        viewModelScope.launch {
            getDownloadUrlUseCase(path, fileName)
                .onSuccess {
                    setCounselorInfo(
                        CounselorInfoUiModel(
                            //                            sdw312 빌드 테스트 임의 id 값
                            currentUser.value?.email ?: "",
                            name ?: "",
                            pr ?: "",
                            it.toString(),
                        ),
                    )
                }
        }
    }

    private fun updateCounselingType() {
        viewModelScope.launch {
            getCurrentUserUseCase()?.uid?.let {
                updateCounselingTypeUseCase(it, getCounselingTypeLocalUseCase())
                    .onSuccess {
                        _isShowProgress.value = false
                        setEffect(UpdateCounselorBasicInfoEffect.SuccessUpdateCounselingType)
                    }
                    .onFailure {
                        setEffect(UpdateCounselorBasicInfoEffect.FailureUpdateCounselingType)
                    }
            }
        }
    }

    private fun setCounselorInfo(info: CounselorInfoUiModel) {
        viewModelScope.launch {
            currentUser.value?.uid?.let {
                setCounselorInfoUseCase(it, info.toDomainModel())
                    .onSuccess {
                        updateOfficeInfo()
                        setEffect(UpdateCounselorBasicInfoEffect.SuccessUpdateCounselorInfo)
                    }
                    .onFailure {
                        setEffect(UpdateCounselorBasicInfoEffect.FailureUpdateCounselorInfo)
                    }
            }
        }
    }

    private fun updateOfficeInfo() {
        viewModelScope.launch {
            _isShowProgress.value = true
            currentUser.value?.uid?.let {
                updateOfficeInfoUseCase(getAllOfficeInfoUseCase()[0])
                    .onSuccess {
                        updateCounselingType()
                        setEffect(UpdateCounselorBasicInfoEffect.SuccessUpdateOfficeInfo)
                    }
                    .onFailure {
                        setEffect(UpdateCounselorBasicInfoEffect.FailureUpdateOfficeInfo)
                    }
            }
        }
    }
}
