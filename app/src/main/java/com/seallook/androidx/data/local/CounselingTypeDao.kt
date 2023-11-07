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
    suspend fun insert(counselingType: CounselingTypeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(counselingTypeList: List<CounselingTypeEntity>)

    @Query("SELECT * FROM CounselingType ORDER BY ID ASC")
    suspend fun getAll(): List<CounselingTypeEntity>

    @Query("SELECT * FROM CounselingType WHERE ID = :id")
    suspend fun getItem(id: Int): CounselingTypeEntity?

    @Query("SELECT * FROM CounselingType WHERE ID = :id AND EMAIL = :email")
    suspend fun getItem(email: String, id: Int): CounselingTypeEntity?

    @Update
    suspend fun update(counselingType: CounselingTypeEntity)

    @Update
    suspend fun update(counselingTypeList: List<CounselingTypeEntity>)

    @Query("DELETE FROM CounselingType")
    suspend fun deleteAll()

    @Query("DELETE FROM CounselingType WHERE ID = :id")
    suspend fun deleteItem(id: Int)
}
