package com.seallook.androidx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.seallook.androidx.data.local.model.CounselingType
import kotlinx.coroutines.flow.Flow

@Dao
interface CounselingTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(counselingType: CounselingType)

    @Query("SELECT * FROM counselingType ORDER BY ID ASC")
    fun getAll(): Flow<List<CounselingType>>

    @Update
    suspend fun update(counselingType: CounselingType)

    @Query("DELETE FROM counselingType WHERE ID = :id")
    suspend fun deleteItem(id: Int)
}
