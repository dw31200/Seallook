package com.seallook.androidx.data.repository

import android.net.Uri
import com.google.firebase.storage.UploadTask

interface FirebaseStorageRepository {
    suspend fun getDownloadUrl(path: String, fileName: String): Uri

    suspend fun uploadFile(path: String, fileName: String, uri: Uri): UploadTask
}
