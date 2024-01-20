package com.seallook.androidx.data.remote.counselor.office

import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.CounselorOfficeIdResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CounselorOfficeIdApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : CounselorOfficeIdApiService {
    override suspend fun get(email: String): CounselorOfficeIdResponse? {
        val documentResponse = db.collection(Constants.COUNSELORS)
            .document(email)
            .collection(Constants.OFFICE_INFO)
            .document(email)
            .get()
            .await()
        return if (documentResponse.exists()) {
            CounselorOfficeIdResponse(documentResponse)
        } else {
            null
        }
    }

    override suspend fun update(counselorOfficeIdResponse: CounselorOfficeIdResponse) {
        db.collection(Constants.COUNSELORS)
            .document(counselorOfficeIdResponse.email)
            .collection(Constants.OFFICE_INFO)
            .document(counselorOfficeIdResponse.email)
            .set(counselorOfficeIdResponse)
            .await()
    }
}
