package com.seallook.androidx.data.repository

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.UploadTask

interface FirebaseStorageRepository {
    suspend fun getDownloadUrl(path: String, fileName: String): Task<Uri>

    suspend fun uploadFile(path: String, fileName: String, uri: Uri): UploadTask
}
