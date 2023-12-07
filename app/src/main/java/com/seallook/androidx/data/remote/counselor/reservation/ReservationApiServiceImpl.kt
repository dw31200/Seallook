package com.seallook.androidx.data.remote.counselor.reservation

import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.ReservationResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ReservationApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : ReservationApiService {
    override suspend fun getClientList(email: String): List<ReservationResponse> {
        val list = mutableListOf<ReservationResponse>()
        val documentListResponse = db.collection(Constants.RESERVATION)
            .whereEqualTo("counselorEmail", email)
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

    override suspend fun getCounselorList(email: String): List<ReservationResponse> {
        val list = mutableListOf<ReservationResponse>()
        val documentListResponse = db.collection(Constants.RESERVATION)
            .whereEqualTo("clientEmail", email)
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
        val ref = db.collection(Constants.RESERVATION)
            .document()
        val setReservation = ReservationResponse(
            id = ref.id,
            counselorEmail = reservation.counselorEmail,
            scheduleId = reservation.scheduleId,
            clientEmail = reservation.clientEmail,
            date = reservation.date,
            confirm = reservation.confirm,
        )
        ref
            .set(setReservation)
            .await()
    }

    override suspend fun update(id: String, confirm: Boolean) {
        db.collection(Constants.RESERVATION)
            .document(id)
            .update("confirm", confirm)
            .await()
    }
}
