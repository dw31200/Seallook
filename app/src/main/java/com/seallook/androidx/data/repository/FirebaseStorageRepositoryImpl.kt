package com.seallook.androidx.data.repository

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.UploadTask
import com.seallook.androidx.data.remote.api.firebase.storage.FirebaseStorageApiService
import javax.inject.Inject

class FirebaseStorageRepositoryImpl @Inject constructor(
    private val firebaseStorageApiService: FirebaseStorageApiService,
) : FirebaseStorageRepository {
    override suspend fun getDownloadUrl(path: String, fileName: String): Task<Uri> {
        return firebaseStorageApiService.getDownloadUrl(path, fileName)
    }

    override suspend fun uploadFile(path: String, fileName: String, uri: Uri): UploadTask {
        return firebaseStorageApiService.uploadFile(path, fileName, uri)
    }
}
