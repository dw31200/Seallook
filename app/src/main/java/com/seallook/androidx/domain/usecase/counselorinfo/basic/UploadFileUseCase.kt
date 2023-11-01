package com.seallook.androidx.domain.usecase.counselorinfo.basic

import android.net.Uri
import com.google.firebase.storage.UploadTask
import com.seallook.androidx.data.repository.FirebaseStorageRepository
import dagger.Reusable
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@Reusable
class UploadFileUseCase @Inject constructor(
    private val firebaseStorageRepository: FirebaseStorageRepository,
) {
    suspend operator fun invoke(path: String, fileName: String, uri: Uri): Result<UploadTask.TaskSnapshot> {
        return runCatching {
            firebaseStorageRepository.uploadFile(path, fileName, uri).await()
        }
    }
}
