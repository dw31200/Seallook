package com.seallook.androidx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.seallook.androidx.data.local.model.CounselingScheduleEntity
import java.time.LocalDate

@Dao
interface CounselingScheduleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(counselingSchedule: CounselingScheduleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(counselingScheduleList: List<CounselingScheduleEntity>)

    @Query("SELECT * FROM CounselingSchedule ORDER BY ID ASC")
    suspend fun getAll(): List<CounselingScheduleEntity>

    @Query("SELECT * FROM CounselingSchedule WHERE EMAIL = :email")
    suspend fun getAll(email: String): List<CounselingScheduleEntity>

    @Query("SELECT * FROM CounselingSchedule WHERE ID = :id")
    suspend fun getItem(id: String): CounselingScheduleEntity?

    @Query("SELECT * FROM CounselingSchedule WHERE DATE > :selectedDate AND DATE < :nextDate")
    suspend fun getCounselingSchedulesOnDate(selectedDate: LocalDate, nextDate: LocalDate): List<CounselingScheduleEntity>

    @Update
    suspend fun update(counselingSchedule: CounselingScheduleEntity)

    @Update
    suspend fun update(counselingScheduleList: List<CounselingScheduleEntity>)

    @Query("DELETE FROM CounselingSchedule")
    suspend fun deleteAll()

    @Query("DELETE FROM CounselingSchedule WHERE ID = :id")
    suspend fun deleteItem(id: String)
}
