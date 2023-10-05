package com.seallook.androidx.data.repository

import android.net.Uri
import com.seallook.androidx.data.remote.FirebaseStorageApiService
import javax.inject.Inject

class FirebaseStorageRepositoryImpl @Inject constructor(
    private val firebaseStorageApiService: FirebaseStorageApiService,
) : FirebaseStorageRepository {
    override suspend fun getDownloadUrl(path: String, fileName: String): Uri? {
        return firebaseStorageApiService.getDownloadUrl(path, fileName)
    }

    override suspend fun uploadFile(path: String, fileName: String, uri: Uri) {
        firebaseStorageApiService.uploadFile(path, fileName, uri)
    }
}
