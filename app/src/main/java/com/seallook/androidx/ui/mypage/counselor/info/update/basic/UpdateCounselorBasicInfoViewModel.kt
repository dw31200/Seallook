package com.seallook.androidx.ui.mypage.counselor.info.update.basic

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.model.CounselorInfoModel
import com.seallook.androidx.domain.model.OfficeInfoModel
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.GetCounselorInfoUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.GetDownloadUrlUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.SetCounselorInfoUsecase
import com.seallook.androidx.domain.usecase.counselorinfo.basic.UploadFileUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.GetCounselingTypeUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.counselingtype.UpdateCounselingTypeUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.office.GetOfficeInfoUseCase
import com.seallook.androidx.domain.usecase.counselorinfo.office.UpdateOfficeInfoUseCase
import com.seallook.androidx.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateCounselorBasicInfoViewModel @Inject constructor(
    private val getDownloadUrlUseCase: GetDownloadUrlUseCase,
    private val uploadFileUseCase: UploadFileUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val updateCounselingTypeUseCase: UpdateCounselingTypeUseCase,
    private val getCounselingTypeUseCase: GetCounselingTypeUseCase,
    private val getCounselorInfoUseCase: GetCounselorInfoUseCase,
    private val setCounselorInfoUsecase: SetCounselorInfoUsecase,
    private val getOfficeInfoUseCase: GetOfficeInfoUseCase,
    private val updateOfficeInfoUseCase: UpdateOfficeInfoUseCase,
) : BaseViewModel() {
    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser
    private val _downloadUrl = MutableLiveData<Uri?>()
    val downloadUrl: LiveData<Uri?>
        get() = _downloadUrl
    private val _counselorInfo = MutableLiveData<CounselorInfoModel?>()
    val counselorInfo: LiveData<CounselorInfoModel?>
        get() = _counselorInfo
    private val _officeInfo = MutableLiveData<OfficeInfoModel?>()
    val officeInfo: LiveData<OfficeInfoModel?>
        get() = _officeInfo

    init {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase()
            _counselorInfo.value = getCounselorInfoUseCase()
            _officeInfo.value = getOfficeInfoUseCase(0)
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
                        CounselorInfoModel(
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
            updateCounselingTypeUseCase(getCounselingTypeUseCase())
        }
    }

    fun setCounselorInfo(info: CounselorInfoModel) {
        viewModelScope.launch {
            setCounselorInfoUsecase(info)
        }
    }

    fun updateOfficeInfo() {
        viewModelScope.launch {
            getOfficeInfoUseCase(0)?.let { updateOfficeInfoUseCase(it) }
        }
    }
}
