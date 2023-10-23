package com.seallook.androidx.data.remote.counselor.counselingtype

import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.seallook.androidx.data.remote.model.CounselingTypeListResponse
import com.seallook.androidx.data.remote.model.CounselingTypeResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CounselingTypeApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : CounselingTypeApiService {
    override suspend fun getAll(uid: String): List<CounselingTypeResponse> {
        val list = mutableListOf<CounselingTypeResponse>()
        val documentResponse = db.collection(Constants.COUNSELORS)
            .document(uid)
            .collection(Constants.COUNSELING_TYPE)
            .document(uid)
            .get()
            .await()
        return if (documentResponse.exists()) {
            val result = Gson().fromJson(documentResponse.getString(Constants.COUNSELING_LIST), List::class.java)
            for (element in result) {
                list.add(Gson().fromJson(element.toString(), CounselingTypeResponse::class.java))
            }
            list
        } else {
            emptyList()
        }
    }

    override suspend fun updateList(uid: String, type: CounselingTypeListResponse) {
        db.collection(Constants.COUNSELORS)
            .document(uid)
            .collection(Constants.COUNSELING_TYPE)
            .document(uid)
            .set(type)
            .await()
    }
}
