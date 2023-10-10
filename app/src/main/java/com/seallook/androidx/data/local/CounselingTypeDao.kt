package com.seallook.androidx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.seallook.androidx.data.local.model.CounselingTypeEntity

@Dao
interface CounselingTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(counselingTypeEntity: CounselingTypeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(counselingTypeEntityList: List<CounselingTypeEntity>)

    @Query("SELECT * FROM counselingTypeEntity ORDER BY ID ASC")
    suspend fun getAll(): List<CounselingTypeEntity>

    @Update
    suspend fun update(counselingTypeEntity: CounselingTypeEntity)

    @Query("DELETE FROM counselingTypeEntity WHERE ID = :id")
    suspend fun deleteItem(id: Int)
}
