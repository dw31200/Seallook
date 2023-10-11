package com.seallook.androidx.ui.mypage.counselor.info.update.basic

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.model.CounselorInfoModel
import com.seallook.androidx.domain.usecase.GetCounselingTypeUseCase
import com.seallook.androidx.domain.usecase.GetCounselorInfoUseCase
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.GetDownloadUrlUseCase
import com.seallook.androidx.domain.usecase.SetCounselorInfoUsecase
import com.seallook.androidx.domain.usecase.UpdateCounselingTypeUseCase
import com.seallook.androidx.domain.usecase.UploadFileUseCase
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

    init {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase()
            _counselorInfo.value = getCounselorInfoUseCase()
        }
    }

    fun uploadFile(path: String, fileName: String, uri: Uri) {
        viewModelScope.launch {
            uploadFileUseCase(path, fileName, uri)
        }
    }

    fun getDownloadUrl(path: String, fileName: String) {
        viewModelScope.launch {
            _downloadUrl.value = getDownloadUrlUseCase(path, fileName)
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
}
