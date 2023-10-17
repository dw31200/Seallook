package com.seallook.androidx.data.remote.counselor.basicinfo

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.CounselorInfoResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CounselorInfoApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : CounselorInfoApiService {
    override suspend fun getItem(user: FirebaseUser?): CounselorInfoResponse? {
        val uid = user?.uid ?: return null
        val documentResponse = db.collection(Constants.COUNSELORS)
            .document(uid)
            .collection("counselorinfo") // Magic String -> 의미를 알 수 없는 문자열
            .document(uid)
            .get()
            .await()
        return if (documentResponse.exists()) {
            CounselorInfoResponse(documentResponse)
        } else {
            null
        }
    }

    override suspend fun setItem(user: FirebaseUser?, info: CounselorInfoResponse): Boolean? {
        return user?.uid?.let {
            runCatching {
                db.collection(Constants.COUNSELORS)
                    .document(it)
                    .collection("counselorinfo")
                    .document(it)
                    .set(info)
                    .await()
            }.fold(
                onSuccess = { true },
                onFailure = { false },
            )
        }
    }
}
