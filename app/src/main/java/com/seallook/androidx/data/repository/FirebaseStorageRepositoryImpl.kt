package com.seallook.androidx.data.repository

import android.net.Uri
import com.google.firebase.storage.UploadTask
import com.seallook.androidx.data.remote.api.firebase.storage.FirebaseStorageApiService
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseStorageRepositoryImpl @Inject constructor(
    private val firebaseStorageApiService: FirebaseStorageApiService,
) : FirebaseStorageRepository {
    override suspend fun getDownloadUrl(path: String, fileName: String): Uri {
        return firebaseStorageApiService
            .getReference(path, fileName)
            .downloadUrl
            .await()
    }

    override suspend fun uploadFile(path: String, fileName: String, uri: Uri): UploadTask {
        return firebaseStorageApiService
            .getReference(path, fileName)
            .putFile(uri)
    }
}
