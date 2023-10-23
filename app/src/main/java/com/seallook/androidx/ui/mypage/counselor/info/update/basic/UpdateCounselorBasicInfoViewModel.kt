package com.seallook.androidx.ui.mypage.counselor.info.update.basic

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.GetCounselorInfoUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.GetDownloadUrlUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.SetCounselorInfoUsecase
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
    private val setCounselorInfoUsecase: SetCounselorInfoUsecase,
    private val getOfficeInfoUseCase: GetOfficeInfoUseCase,
    private val getAllOfficeInfoUseCase: GetAllOfficeInfoUseCase,
    private val updateOfficeInfoUseCase: UpdateOfficeInfoUseCase,
) : BaseViewModel() {
    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser
    private val _downloadUrl = MutableLiveData<Uri?>()
    val downloadUrl: LiveData<Uri?>
        get() = _downloadUrl
    private val _counselorInfo = MutableLiveData<CounselorInfoUiModel?>()
    val counselorInfo: LiveData<CounselorInfoUiModel?>
        get() = _counselorInfo
    private val _officeInfo = MutableLiveData<OfficeInfoUiModel?>()
    val officeInfo: LiveData<OfficeInfoUiModel?>
        get() = _officeInfo
    private val _uploadBasicInfoResult = MutableLiveData<Boolean?>()
    private val _uploadOfficeInfoResult = MutableLiveData<Boolean?>()
    private val _uploadCounselingTypeResult = MutableLiveData<Boolean?>()
    val uploadResult: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(_uploadBasicInfoResult) { basicInfoResult ->
            val officeInfoResult = _uploadOfficeInfoResult.value
            val counselingTypeResult = _uploadCounselingTypeResult.value
            value = basicInfoResult == true && officeInfoResult == true && counselingTypeResult == true
        }

        addSource(_uploadOfficeInfoResult) { officeInfoResult ->
            val basicInfoResult = _uploadBasicInfoResult.value
            val counselingTypeResult = _uploadCounselingTypeResult.value
            value = basicInfoResult == true && officeInfoResult == true && counselingTypeResult == true
        }

        addSource(_uploadCounselingTypeResult) { counselingTypeResult ->
            val basicInfoResult = _uploadBasicInfoResult.value
            val officeInfoResult = _uploadOfficeInfoResult.value
            value = basicInfoResult == true && officeInfoResult == true && counselingTypeResult == true
        }
    }

    init {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase()
            _counselorInfo.value = getCounselorInfoUseCase()?.let { CounselorInfoUiModel(it) }
            _officeInfo.value = getOfficeInfoUseCase(0)?.let { OfficeInfoUiModel(it) }
        }
    }

    fun uploadFile(path: String, fileName: String, uri: Uri, name: String?, pr: String?) {
        viewModelScope.launch {
            uploadFileUseCase(path, fileName, uri)
                .addOnSuccessListener {
                    getDownloadUrl(path, fileName, name, pr)
                }
        }
    }

    private fun getDownloadUrl(path: String, fileName: String, name: String?, pr: String?) {
        viewModelScope.launch {
            getDownloadUrlUseCase(path, fileName)
                .addOnSuccessListener {
                    _downloadUrl.value = it
                    setCounselorInfo(
                        CounselorInfoUiModel(
                            //                            sdw312 빌드 테스트 임의 id 값
                            0,
                            name ?: "",
                            pr ?: "",
                            downloadUrl.value.toString(),
                        ),
                    )
                }
        }
    }

    fun updateCounselingType() {
        viewModelScope.launch {
            _uploadCounselingTypeResult.value =
                getCurrentUserUseCase()?.uid?.let { updateCounselingTypeUseCase(it, getCounselingTypeLocalUseCase()) }
        }
    }

    fun setCounselorInfo(info: CounselorInfoUiModel) {
        viewModelScope.launch {
            _uploadBasicInfoResult.value = setCounselorInfoUsecase(info.toDomainModel())
        }
    }

    fun updateOfficeInfo() {
        viewModelScope.launch {
            _uploadOfficeInfoResult.value = updateOfficeInfoUseCase(getAllOfficeInfoUseCase()[0])
        }
    }
}
