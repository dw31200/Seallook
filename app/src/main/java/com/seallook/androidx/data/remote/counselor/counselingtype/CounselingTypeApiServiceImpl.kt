package com.seallook.androidx.data.remote.counselor.counselingtype

import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.CounselingTypeResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CounselingTypeApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : CounselingTypeApiService {
    override suspend fun getAll(email: String): List<CounselingTypeResponse> {
        val list = mutableListOf<CounselingTypeResponse>()
        val documentResponse = db.collection(Constants.COUNSELORS)
            .document(email)
            .collection(Constants.COUNSELING_TYPE)
            .get()
            .await()
        if (!documentResponse.isEmpty) {
            for (document in documentResponse) {
                if (document.exists()) {
                    CounselingTypeResponse(document)?.let { list.add(it) }
                }
            }
        }
        return list
    }

    override suspend fun updateList(email: String, type: List<CounselingTypeResponse>) {
        db.collection(Constants.COUNSELORS)
            .document(email)
            .collection(Constants.COUNSELING_TYPE)
            .document(Constants.TEST_ID)
            .set(type)
            .await()
    }
}
