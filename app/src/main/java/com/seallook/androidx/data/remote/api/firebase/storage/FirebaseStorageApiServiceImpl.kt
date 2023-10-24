package com.seallook.androidx.data.remote.api.firebase.storage

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

class FirebaseStorageApiServiceImpl @Inject constructor(
    private val storage: FirebaseStorage,
) : FirebaseStorageApiService {
    override suspend fun getReference(path: String, fileName: String): StorageReference {
        return storage.reference.child(path).child(fileName)
    }
}
