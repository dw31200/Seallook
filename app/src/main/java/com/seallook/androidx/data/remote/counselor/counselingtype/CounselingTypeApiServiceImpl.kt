package com.seallook.androidx.data.remote.counselor.counselingtype

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.CounselingTypeListResponse
import com.seallook.androidx.data.remote.model.CounselingTypeResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CounselingTypeApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : CounselingTypeApiService {
    override suspend fun getList(user: FirebaseUser?): List<CounselingTypeResponse> {
        val uid = user?.uid ?: return emptyList()
        val documentResponse = db.collection(Constants.COUNSELORS)
            .document(uid)
            .collection("counselingType")
            .document(uid)
            .get().await()
        return if (documentResponse.exists()) {
            CounselingTypeListResponse(documentResponse)
        } else {
            emptyList()
        }
    }

//    override suspend fun getList(user: FirebaseUser?): List<CounselingTypeResponse> {
//        val uid = user?.uid ?: return listOf()
//        val list = mutableListOf<CounselingTypeResponse>()
//        val documentResponse = db.collection(Constants.COUNSELORS)
//            .document(uid)
//            .collection("counselingType")
//            .document(uid)
//            .get().await()
//        return if (documentResponse.exists()) {
//            val result = Gson().fromJson(documentResponse.getString("counselingList"), List::class.java)
//            for (i in 0 until result.size) {
//                list.add(Gson().fromJson(result[i].toString(), CounselingTypeResponse::class.java))
//            }
//            list
//        } else {
//            emptyList()
//        }
//    }
    override suspend fun updateList(user: FirebaseUser?, type: CounselingTypeListResponse) {
        user?.uid?.let {
            db.collection(Constants.COUNSELORS)
                .document(it)
                .collection("counselingType")
                .document(it)
                .set(type.toJson())
        }
    }
}
