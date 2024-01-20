package com.seallook.androidx.data.remote.counselor.office

import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.OfficeCounselorEmailResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class OfficeCounselorEmailApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : OfficeCounselorEmailApiService {
    override suspend fun getList(officeId: String): List<OfficeCounselorEmailResponse> {
        val list = mutableListOf<OfficeCounselorEmailResponse>()
        val documentListResponse = db.collection(Constants.OFFICE_INFO)
            .document(officeId)
            .collection(Constants.COUNSELORS)
            .get()
            .await()
        if (!documentListResponse.isEmpty) {
            for (document in documentListResponse) {
                val documentSnapshot = db.collection(Constants.OFFICE_INFO)
                    .document(document.id)
                    .collection(Constants.COUNSELORS)
                    .document(document.id)
                    .get()
                    .await()
                if (documentSnapshot.exists()) {
                    OfficeCounselorEmailResponse(documentSnapshot)?.let { list.add(it) }
                }
            }
        }
        return list
    }

    override suspend fun getList(officeId: String, email: String): List<OfficeCounselorEmailResponse> {
        val documentResponse = db.collection(Constants.OFFICE_INFO)
            .document(officeId)
            .collection(Constants.COUNSELORS)
            .whereEqualTo("counselorEmail", email)
            .get()
            .await()
        return if (!documentResponse.isEmpty) {
            OfficeCounselorEmailResponse(documentResponse)
        } else {
            emptyList()
        }
    }

    override suspend fun set(officeCounselorEmailResponse: OfficeCounselorEmailResponse) {
        val ref = db.collection(Constants.OFFICE_INFO)
            .document(officeCounselorEmailResponse.officeId)
            .collection(Constants.COUNSELORS)
            .document()

        val setOfficeCounselorEmail = OfficeCounselorEmailResponse(
            id = ref.id,
            officeId = officeCounselorEmailResponse.officeId,
            counselorEmail = officeCounselorEmailResponse.counselorEmail,
        )
        ref
            .set(setOfficeCounselorEmail)
            .await()
    }
}
