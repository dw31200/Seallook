package com.seallook.androidx.ui.mypage.counselor.info.update.basic

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.GetCounselorInfoUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.GetDownloadUrlUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.SetCounselorInfoUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.UploadFileUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.office.GetCounselorOfficeIdUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.office.GetOfficeInfoUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.office.SetOfficeCounselorEmailUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.office.UpdateCounselorOfficeIdUseCase
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
    private val getCounselorInfoUseCase: GetCounselorInfoUseCase,
    private val setCounselorInfoUseCase: SetCounselorInfoUseCase,
    private val getOfficeInfoUseCase: GetOfficeInfoUseCase,
    private val updateOfficeInfoUseCase: UpdateOfficeInfoUseCase,
    getUserTypeUseCase: GetUserTypeUseCase,
    private val getCounselorOfficeIdUseCase: GetCounselorOfficeIdUseCase,
    private val updateCounselorOfficeIdUseCase: UpdateCounselorOfficeIdUseCase,
    private val setOfficeCounselorEmailUseCase: SetOfficeCounselorEmailUseCase,
) : BaseViewModel<UpdateCounselorBasicInfoEffect>() {
    val userType: LiveData<UserTypeUiModel?> = getUserTypeUseCase().map {
        it?.let {
            UserTypeUiModel(it)
        }
    }.asLiveData()

    private val _currentUser = MutableLiveData<FirebaseUser?>()
    private val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    private val officeId: LiveData<String?> =
        userType.switchMap {
            liveData {
                getCounselorOfficeIdUseCase(it?.email)
                    .onSuccess {
                        emit(it?.officeId)
                    }
            }
        }

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

    val officeInfo: LiveData<OfficeInfoUiModel?> =
        officeId.switchMap {
            it?.let {
                liveData {
                    getOfficeInfoUseCase(it)?.let {
                        emit(OfficeInfoUiModel(it))
                    }
                }
            }
        }

    init {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase()
        }
    }

    fun uploadFile(path: String, uri: Uri, name: String?, pr: String?) {
        viewModelScope.launch {
            val fileName = "${currentUser.value}.png"
            setEffect(UpdateCounselorBasicInfoEffect.UploadFile)
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
                    setEffect(UpdateCounselorBasicInfoEffect.FailureUpdateCounselorInfo)
                }
        }
    }

    private fun updateOfficeInfo() {
        viewModelScope.launch {
            updateOfficeInfoUseCase(officeInfo.value?.toDomainModel())
                .onSuccess {
                    updateCounselorOfficeId()
                    setEffect(UpdateCounselorBasicInfoEffect.SuccessUpdateOfficeInfo)
                }
                .onFailure {
                    setEffect(UpdateCounselorBasicInfoEffect.FailureUpdateOfficeInfo)
                }
        }
    }

    private fun updateCounselorOfficeId() {
        viewModelScope.launch {
            updateCounselorOfficeIdUseCase(
                UpdateCounselorOfficeIdUseCase.Params(
                    userType.value?.email,
                    officeId.value,
                ),
            )
                .onSuccess {
                    setOfficeCounselorEmail()
                    setEffect(UpdateCounselorBasicInfoEffect.SuccessUpdateCounselorOfficeId)
                }
                .onFailure {
                    setEffect(UpdateCounselorBasicInfoEffect.FailureUpdateCounselorOfficeId)
                }
        }
    }

    private fun setOfficeCounselorEmail() {
        viewModelScope.launch {
            setOfficeCounselorEmailUseCase(
                SetOfficeCounselorEmailUseCase.Params(
                    "",
                    officeId.value,
                    userType.value?.email,
                ),
            )
                .onSuccess {
                    setEffect(UpdateCounselorBasicInfoEffect.SuccessSetOfficeCounselorEmail)
                }
                .onFailure {
                    setEffect(UpdateCounselorBasicInfoEffect.FailureSetOfficeCounselorEmail)
                }
        }
    }
}
