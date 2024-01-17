package com.seallook.androidx.data.repository.counselor.reservation

import com.seallook.androidx.data.local.ReservationDao
import com.seallook.androidx.data.model.Reservation
import com.seallook.androidx.data.remote.counselor.reservation.ReservationApiService
import com.seallook.androidx.share.UserTypeOption
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val reservationApiService: ReservationApiService,
    private val reservationDao: ReservationDao,
) : ReservationRepository {
    override fun getItem(id: String): Flow<Reservation?> {
        return reservationDao.getItem(id).map {
            it?.let {
                Reservation(it)
            }
        }
    }

    override fun getList(email: String, userType: UserTypeOption): Flow<List<Reservation>> {
        return reservationDao.getList(email, userType).map {
            it.map {
                Reservation(it)
            }
        }
    }

    override fun getClientList(email: String): Flow<List<Reservation>> {
        return reservationDao.getClientList(email).map {
            it.map {
                Reservation(it)
            }
        }
    }

    override fun getCounselingList(email: String): Flow<List<Reservation>> {
        return reservationDao.getCounselingList(email).map {
            it.map {
                Reservation(it)
            }
        }
    }

    override fun onListSnapshot(email: String, userType: UserTypeOption): Flow<List<Reservation>> {
        return reservationApiService.onListSnapshot(email, userType).map {
            it.map {
                Reservation(it)
            }
        }
    }

    override fun onCounselingListSnapshot(email: String): Flow<List<Reservation>> {
        return reservationApiService.onCounselingListSnapshot(email).map {
            it.map {
                Reservation(it)
            }
        }
    }

    override fun onClientListSnapshot(email: String): Flow<List<Reservation>> {
        return reservationApiService.onClientListSnapshot(email).map {
            it.map {
                Reservation(it)
            }
        }
    }

    override suspend fun insert(reservation: Reservation) {
        reservationDao.insert(reservation.toLocalModel())
    }

    override suspend fun insert(reservationList: List<Reservation>) {
        reservationDao.insert(
            reservationList.map {
                it.toLocalModel()
            },
        )
    }

    override suspend fun set(reservation: Reservation) {
        reservationApiService.set(reservation.toRemoteModel())
    }

    override suspend fun update(id: String, confirm: Boolean) {
        reservationApiService.update(id, confirm)
    }
}
