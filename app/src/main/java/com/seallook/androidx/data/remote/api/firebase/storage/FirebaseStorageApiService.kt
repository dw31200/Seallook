package com.seallook.androidx.data.remote.api.firebase.storage

import com.google.firebase.storage.StorageReference

interface FirebaseStorageApiService {
    suspend fun getReference(path: String, fileName: String): StorageReference
}
