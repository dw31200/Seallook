package com.seallook.androidx.data.remote.counselor.list

import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.CounselorListResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

// sdw312 리스트 가져오기 테스트 > counselorinfo 로 이동 필요
class CounselorListApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : CounselorListApiService {
    override suspend fun getAll(): List<CounselorListResponse> {
        val list = mutableListOf<CounselorListResponse>()
        val documentListResponse = db.collection(Constants.COUNSELORS)
            .get()
            .await()
        if (!documentListResponse.isEmpty) {
            for (document in documentListResponse) {
                val documentSnapshot = db.collection(Constants.COUNSELORS)
                    .document(document.id)
                    .collection(Constants.COUNSELOR_INFO)
                    .document("1")
                    .get()
                    .await()
                if (documentSnapshot.exists()) {
                    CounselorListResponse(documentSnapshot)?.let { list.add(it) }
                }
            }
        }
        return list
    }
}
