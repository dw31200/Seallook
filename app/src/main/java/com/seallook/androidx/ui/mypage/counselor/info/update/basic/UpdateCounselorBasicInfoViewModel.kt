package com.seallook.androidx.ui.mypage.counselor.info.update.basic

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
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
import com.seallook.androidx.domain.usecase.usertype.GetUserTypeUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import com.seallook.androidx.ui.model.CounselorInfoUiModel
import com.seallook.androidx.ui.model.OfficeInfoUiModel
import com.seallook.androidx.ui.model.UserTypeUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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
    private val getUserTypeUseCase: GetUserTypeUseCase,
) : BaseViewModel<UpdateCounselorBasicInfoEffect>() {
    val userType: LiveData<UserTypeUiModel?> = getUserTypeUseCase().map {
        it?.let {
            UserTypeUiModel(it)
        }
    }.asLiveData()

    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    val counselorInfo: LiveData<CounselorInfoUiModel?> =
        userType.asFlow().flatMapLatest {
            flow {
                emit(
                    getCounselorInfoUseCase(
                        GetCounselorInfoUseCase.Params(
                            it?.email,
                        ),
                    )?.let {
                        CounselorInfoUiModel(it)
                    },
                )
            }
        }.asLiveData()

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
            _officeInfo.value =
//                TODO sdw312 임시 id
                getOfficeInfoUseCase("0")?.let {
                    OfficeInfoUiModel(it)
                }
        }
    }

    fun uploadFile(path: String, fileName: String, uri: Uri, name: String?, pr: String?) {
        viewModelScope.launch {
            _isShowProgress.value = true
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
                        name,
                        pr,
                        it.toString(),
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
                        _isShowProgress.value = false
                        setEffect(UpdateCounselorBasicInfoEffect.FailureUpdateCounselingType)
                    }
            }
        }
    }

    private fun setCounselorInfo(name: String?, description: String?, thumbnail: String?) {
        viewModelScope.launch {
            setCounselorInfoUseCase(
                SetCounselorInfoUseCase.Params(
                    userType.value?.email,
                    name,
                    description,
                    thumbnail,
                ),
            )
                .onSuccess {
                    updateOfficeInfo()
                    setEffect(UpdateCounselorBasicInfoEffect.SuccessUpdateCounselorInfo)
                }
                .onFailure {
                    _isShowProgress.value = false
                    setEffect(UpdateCounselorBasicInfoEffect.FailureUpdateCounselorInfo)
                }
        }
    }

    private fun updateOfficeInfo() {
        viewModelScope.launch {
            currentUser.value?.uid?.let {
                updateOfficeInfoUseCase(getAllOfficeInfoUseCase()[0])
                    .onSuccess {
                        updateCounselingType()
                        setEffect(UpdateCounselorBasicInfoEffect.SuccessUpdateOfficeInfo)
                    }
                    .onFailure {
                        _isShowProgress.value = false
                        setEffect(UpdateCounselorBasicInfoEffect.FailureUpdateOfficeInfo)
                    }
            }
        }
    }
}
