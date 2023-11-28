package com.seallook.androidx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.seallook.androidx.data.local.model.CounselorInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CounselorInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(counselorInfo: CounselorInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(counselorInfoList: List<CounselorInfoEntity>)

    @Query("SELECT * FROM CounselorInfo ORDER BY email")
    fun getAll(): Flow<List<CounselorInfoEntity>>

    @Query("SELECT * FROM CounselorInfo WHERE email =:email")
    suspend fun getItem(email: String): CounselorInfoEntity

    @Update
    suspend fun update(counselorInfo: CounselorInfoEntity)

    @Update
    suspend fun update(counselorInfoList: List<CounselorInfoEntity>)

    @Query("DELETE FROM CounselorInfo")
    suspend fun deleteAll()

    @Query("DELETE FROM CounselorInfo WHERE email =:email")
    suspend fun deleteItem(email: String)
}
