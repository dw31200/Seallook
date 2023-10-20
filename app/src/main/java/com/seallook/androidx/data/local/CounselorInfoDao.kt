package com.seallook.androidx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.seallook.androidx.data.local.model.CounselorInfoEntity

@Dao
interface CounselorInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(counselorInfo: CounselorInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(counselorInfoList: List<CounselorInfoEntity>)

    @Query("SELECT * FROM CounselorInfo ORDER BY ID")
    suspend fun getAll(): List<CounselorInfoEntity>

    @Query("SELECT * FROM CounselorInfo WHERE ID =:id")
    suspend fun getItem(id: Int): CounselorInfoEntity

    @Update
    suspend fun update(counselorInfo: CounselorInfoEntity)

    @Update
    suspend fun update(counselorInfoList: List<CounselorInfoEntity>)

    @Query("DELETE FROM CounselorInfo")
    suspend fun deleteAll()

    @Query("DELETE FROM CounselorInfo WHERE ID =:id")
    suspend fun deleteItem(id: Int)
}
