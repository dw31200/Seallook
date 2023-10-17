package com.seallook.androidx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.seallook.androidx.data.local.model.CounselingScheduleEntity

/**
 * Dao -> Data Access Object
 * CRUD -> Create, Read, Update, Delete
 * one, more
 */
@Dao
interface CounselingScheduleDao {
    // Method OverLoading
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(counselingScheduleEntity: CounselingScheduleEntity)
    // suspend fun insert(counselingScheduleEntity: CounselingScheduleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(counselingScheduleEntityList: List<CounselingScheduleEntity>)
    // suspend fun insert(counselingScheduleEntityList: List<CounselingScheduleEntity>)

    @Query("SELECT * FROM counselingScheduleEntity ORDER BY ID ASC")
    suspend fun getAll(): List<CounselingScheduleEntity>

    @Query("SELECT * FROM counselingScheduleEntity WHERE ID = :id")
    suspend fun getItemById(id: Int): CounselingScheduleEntity

    @Update
    suspend fun updateItem(counselingScheduleEntity: CounselingScheduleEntity)

    @Query("DELETE FROM counselingScheduleEntity WHERE ID = :id")
    suspend fun deleteItem(id: Int)
}
