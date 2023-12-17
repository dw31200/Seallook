package com.seallook.androidx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.seallook.androidx.data.local.model.ReservationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ReservationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reservation: ReservationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reservationList: List<ReservationEntity>)

    @Query("SELECT * FROM Reservation ORDER BY ID ASC")
    fun getAll(): Flow<List<ReservationEntity>>

    @Query("SELECT * FROM Reservation WHERE counselorEmail = :email")
    fun getClientList(email: String): Flow<List<ReservationEntity>>

    @Query("SELECT * FROM Reservation WHERE clientEmail = :email")
    fun getCounselingList(email: String): Flow<List<ReservationEntity>>

    @Query("SELECT * FROM Reservation WHERE ID = :id")
    fun getItem(id: Int): Flow<ReservationEntity?>

    @Update
    suspend fun update(reservation: ReservationEntity)

    @Update
    suspend fun update(reservationList: List<ReservationEntity>)

    @Query("DELETE FROM Reservation")
    suspend fun deleteAll()

    @Query("DELETE FROM Reservation WHERE ID = :id")
    suspend fun deleteItem(id: Int)
}
