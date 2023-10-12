package com.seallook.androidx.data.remote

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import javax.inject.Inject

class FirebaseStorageApiServiceImpl @Inject constructor(
    private val storage: FirebaseStorage,
) : FirebaseStorageApiService {
    override suspend fun getDownloadUrl(path: String, fileName: String): Task<Uri> {
        return storage.reference.child(path).child(fileName)
            .downloadUrl
    }

    override suspend fun uploadFile(path: String, fileName: String, uri: Uri): UploadTask {
        return storage.reference.child(path).child(fileName)
            .putFile(uri)
    }
}
