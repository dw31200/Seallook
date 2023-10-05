package com.seallook.androidx.ui.mypage.counselor.info.update.basic

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.domain.usecase.GetCurrentUserUseCase
import com.seallook.androidx.domain.usecase.GetDownloadUrlUseCase
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
) : BaseViewModel() {
    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    private val _downloadUrl = MutableLiveData<Uri?>()
    val downloadUrl: LiveData<Uri?>
        get() = _downloadUrl

    init {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase()
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
}
