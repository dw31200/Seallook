package com.seallook.androidx.data.remote.counselor.schedule

import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.CounselingScheduleResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CounselingScheduleApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : CounselingScheduleApiService {
    override suspend fun getAll(email: String): List<CounselingScheduleResponse> {
        val list = mutableListOf<CounselingScheduleResponse>()
        val documentListResponse = db.collection(Constants.COUNSELORS)
            .document(email)
            .collection(Constants.COUNSELING_SCHEDULE)
            .get()
            .await()
        if (!documentListResponse.isEmpty) {
            for (document in documentListResponse) {
                if (document.exists()) {
                    CounselingScheduleResponse(document)?.let { list.add(it) }
                }
            }
        }
        return list
    }
}
