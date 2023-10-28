package com.seallook.androidx.data.remote.counselor.counselingtype

import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.CounselingTypeListResponse
import com.seallook.androidx.data.remote.model.CounselingTypeResponse
import com.seallook.androidx.share.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.tasks.await
import java.lang.reflect.Type
import javax.inject.Inject

class CounselingTypeApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val moshi: Moshi,
) : CounselingTypeApiService {
    override suspend fun getAll(uid: String): List<CounselingTypeResponse> {
        val documentResponse = db.collection(Constants.COUNSELORS)
            .document(uid)
            .collection(Constants.COUNSELING_TYPE)
            .document(uid)
            .get()
            .await()
        return if (documentResponse.exists()) {
            val listMyData: Type = Types.newParameterizedType(
                MutableList::class.java,
                CounselingTypeResponse::class.java,
            )

            val adapter = moshi.adapter<List<CounselingTypeResponse>>(listMyData)
            val document = documentResponse.getString(Constants.COUNSELING_LIST)

            document?.let {
                adapter.fromJson(it)
            } ?: emptyList()
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
