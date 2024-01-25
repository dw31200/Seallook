package com.seallook.androidx.data.remote.counselor.reservation

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import com.seallook.androidx.data.remote.model.ReservationResponse
import com.seallook.androidx.share.Constants
import com.seallook.androidx.share.UserTypeOption
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ReservationApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : ReservationApiService {
    override fun onListSnapshot(email: String, userType: UserTypeOption): Flow<List<ReservationResponse>> {
        return when (userType) {
            UserTypeOption.CLIENT -> {
                db.collection(Constants.RESERVATION)
                    .whereEqualTo("clientEmail", email)
                    .dataObjects()
            }
            UserTypeOption.COUNSELOR -> {
                db.collection(Constants.RESERVATION)
                    .whereEqualTo("counselorEmail", email)
                    .dataObjects()
            }
            else -> {
                flow { emit(emptyList()) }
            }
        }
    }

    override fun onCounselingListSnapshot(email: String): Flow<List<ReservationResponse>> {
        return db.collection(Constants.RESERVATION)
            .whereEqualTo("clientEmail", email)
            .dataObjects()
    }

    override fun onClientListSnapshot(email: String): Flow<List<ReservationResponse>> {
        return db.collection(Constants.RESERVATION)
            .whereEqualTo("counselorEmail", email)
            .dataObjects()
    }

    override suspend fun set(reservation: ReservationResponse): Result<ReservationResponse> {
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
        return runCatching {
            ref
                .set(setReservation)
                .await()
        }
            .mapCatching {
                setReservation
            }
    }

    override suspend fun update(id: String, confirm: Boolean) {
        db.collection(Constants.RESERVATION)
            .document(id)
            .update("confirm", confirm)
            .await()
    }
}
