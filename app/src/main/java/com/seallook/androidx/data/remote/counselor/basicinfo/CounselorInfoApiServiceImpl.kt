package com.seallook.androidx.data.remote.counselor.basicinfo

import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.CounselorInfoResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CounselorInfoApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : CounselorInfoApiService {
    override suspend fun getItem(email: String): CounselorInfoResponse? {
        val documentResponse = db.collection(Constants.COUNSELORS)
            .document(email)
            .collection(Constants.COUNSELOR_INFO)
            .document(email)
            .get()
            .await()
        return if (documentResponse.exists()) {
            CounselorInfoResponse(documentResponse)
        } else {
            null
        }
    }

    override suspend fun getAll(): List<CounselorInfoResponse> {
        val list = mutableListOf<CounselorInfoResponse>()
        val documentListResponse = db.collection(Constants.COUNSELORS)
            .get()
            .await()
        if (!documentListResponse.isEmpty) {
            for (document in documentListResponse) {
                val documentSnapshot = db.collection(Constants.COUNSELORS)
                    .document(document.id)
                    .collection(Constants.COUNSELOR_INFO)
                    .document(document.id)
                    .get()
                    .await()
                if (documentSnapshot.exists()) {
                    CounselorInfoResponse(documentSnapshot)?.let { list.add(it) }
                }
            }
        }
        return list
    }

    override suspend fun setItem(info: CounselorInfoResponse) {
        db.collection(Constants.COUNSELORS)
            .document(info.email)
            .collection(Constants.COUNSELOR_INFO)
            .document(info.email)
            .set(info)
            .await()
    }
}
