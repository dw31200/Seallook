package com.seallook.androidx.data.remote.counselor.office

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.api.naver.NaverSearchApi
import com.seallook.androidx.data.remote.model.OfficeInfoResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class OfficeInfoApiServiceImpl @Inject constructor(
    private val naverSearchApi: NaverSearchApi,
    private val db: FirebaseFirestore,
) : OfficeInfoApiService {
    override suspend fun getList(type: String, query: String): List<OfficeInfoResponse> {
        return runCatching {
            naverSearchApi.getList(type = type, query = query)
        }.fold(
            onSuccess = {
                it.items.mapIndexed { index, naverSearchResponse ->
                    OfficeInfoResponse(index, naverSearchResponse)
                }
            },
            onFailure = { emptyList() },
        )
    }

    override suspend fun getItem(user: FirebaseUser?): OfficeInfoResponse? {
        val uid = user?.uid ?: return null
        val documentResponse = db.collection(Constants.COUNSELORS)
            .document(uid)
            .collection("officeinfo")
            .document(uid)
            .get().await()
        return if (documentResponse.exists()) {
            OfficeInfoResponse(documentResponse)
        } else {
            null
        }
    }

    override suspend fun updateItem(user: FirebaseUser?, info: OfficeInfoResponse) {
        user?.uid?.let {
            db.collection(Constants.COUNSELORS)
                .document(it)
                .collection("officeinfo")
                .document(it)
                .set(info)
        }
    }
}
