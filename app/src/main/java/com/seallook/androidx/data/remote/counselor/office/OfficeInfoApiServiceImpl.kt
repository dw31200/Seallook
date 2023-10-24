package com.seallook.androidx.data.remote.counselor.office

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

    override suspend fun getItem(uid: String): OfficeInfoResponse? {
        val documentResponse = db.collection(Constants.COUNSELORS)
            .document(uid)
            .collection(Constants.OFFICE_INFO)
            .document(uid)
            .get().await()
        return if (documentResponse.exists()) {
            OfficeInfoResponse(documentResponse)
        } else {
            null
        }
    }

    override suspend fun updateItem(uid: String, info: OfficeInfoResponse) {
        db.collection(Constants.COUNSELORS)
            .document(uid)
            .collection(Constants.OFFICE_INFO)
            .document(uid)
            .set(info)
            .await()
    }
}
