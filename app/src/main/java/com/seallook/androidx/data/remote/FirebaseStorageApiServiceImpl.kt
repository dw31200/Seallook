package com.seallook.androidx.data.remote

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class FirebaseStorageApiServiceImpl @Inject constructor(
    private val storage: FirebaseStorage,
) : FirebaseStorageApiService {
    override suspend fun getDownloadUrl(path: String, fileName: String): Uri? {
        return runCatching {
            storage.reference.child(path).child(fileName)
                .downloadUrl
        }.fold(
            onSuccess = { it.result },
            onFailure = { null },
        )
    }

    override suspend fun uploadFile(path: String, fileName: String, uri: Uri) {
        storage.reference.child(path).child(fileName)
            .putFile(uri)
    }
}
