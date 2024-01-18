package com.seallook.androidx.data.remote.counselor.office

import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.OfficeInfoResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class OfficeInfoApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : OfficeInfoApiService {
    override suspend fun getItem(id: String): OfficeInfoResponse? {
        val documentResponse = db.collection(Constants.OFFICE_INFO)
            .document(id)
            .get()
            .await()
        return if (documentResponse.exists()) {
            OfficeInfoResponse(documentResponse)
        } else {
            null
        }
    }

    override suspend fun updateItem(info: OfficeInfoResponse) {
        db.collection(Constants.OFFICE_INFO)
            .document(info.id)
            .set(info)
            .await()
    }
}
