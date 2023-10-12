package com.seallook.androidx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.seallook.androidx.data.local.model.CounselingScheduleEntity

@Dao
interface CounselingScheduleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(counselingScheduleEntity: CounselingScheduleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(counselingScheduleEntityList: List<CounselingScheduleEntity>)

    @Query("SELECT * FROM counselingScheduleEntity ORDER BY ID ASC")
    suspend fun getAll(): List<CounselingScheduleEntity>

    @Update
    suspend fun update(counselingScheduleEntity: CounselingScheduleEntity)

    @Query("DELETE FROM counselingScheduleEntity WHERE ID = :id")
    suspend fun deleteItem(id: Int)
}
