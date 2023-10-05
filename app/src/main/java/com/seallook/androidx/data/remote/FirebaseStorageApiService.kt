package com.seallook.androidx.data.remote

import android.net.Uri

interface FirebaseStorageApiService {
    suspend fun getDownloadUrl(path: String, fileName: String): Uri?

    suspend fun uploadFile(path: String, fileName: String, uri: Uri)
}
