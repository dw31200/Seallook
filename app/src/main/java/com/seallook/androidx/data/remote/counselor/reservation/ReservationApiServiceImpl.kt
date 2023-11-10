package com.seallook.androidx.data.remote.counselor.reservation

import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.ReservationResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ReservationApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : ReservationApiService {
    override suspend fun getList(email: String): List<ReservationResponse> {
        val list = mutableListOf<ReservationResponse>()
        val documentListResponse = db.collection(Constants.COUNSELORS)
            .document(email)
            .collection(Constants.RESERVATION)
            .get()
            .await()
        if (!documentListResponse.isEmpty) {
            for (document in documentListResponse) {
                if (document.exists()) {
                    ReservationResponse(document)?.let { list.add(it) }
                }
            }
        }
        return list
    }

    override suspend fun set(reservation: ReservationResponse) {
        db.collection(Constants.COUNSELORS)
            .document(reservation.counselorEmail)
            .collection(Constants.RESERVATION)
            .document(reservation.id.toString())
            .set(reservation)
            .await()
    }
}
