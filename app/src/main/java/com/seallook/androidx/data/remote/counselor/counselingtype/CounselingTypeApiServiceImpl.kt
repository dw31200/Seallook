package com.seallook.androidx.data.remote.counselor.counselingtype

import com.google.firebase.firestore.FirebaseFirestore
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
    override suspend fun getAll(email: String): List<CounselingTypeResponse> {
        val documentResponse = db.collection(Constants.COUNSELORS)
            .document(email)
            .collection(Constants.COUNSELING_TYPE)
            .document(Constants.TEST_ID)
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

    override suspend fun updateList(email: String, type: List<CounselingTypeResponse>) {
        db.collection(Constants.COUNSELORS)
            .document(email)
            .collection(Constants.COUNSELING_TYPE)
            .document(Constants.TEST_ID)
            .set(type)
            .await()
    }
}
