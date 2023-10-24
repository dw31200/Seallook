package com.seallook.androidx.data.remote.counselor.basicinfo

import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.CounselorInfoResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CounselorInfoApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : CounselorInfoApiService {
    override suspend fun getItem(uid: String): CounselorInfoResponse? {
        val documentResponse = db.collection(Constants.COUNSELORS)
            .document(uid)
            .collection(Constants.COUNSELOR_INFO)
            .document(uid)
            .get()
            .await()
        return if (documentResponse.exists()) {
            CounselorInfoResponse(documentResponse)
        } else {
            null
        }
    }

    override suspend fun setItem(uid: String, info: CounselorInfoResponse): Boolean {
        return runCatching {
            db.collection(Constants.COUNSELORS)
                .document(uid)
                .collection(Constants.COUNSELOR_INFO)
                .document(uid)
                .set(info)
                .await()
        }.fold(
            onSuccess = { true },
            onFailure = { false },
        )
    }
}
