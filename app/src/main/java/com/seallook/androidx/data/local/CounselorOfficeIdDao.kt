package com.seallook.androidx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.seallook.androidx.data.local.model.CounselorOfficeIdEntity

@Dao
interface CounselorOfficeIdDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(counselorOfficeId: CounselorOfficeIdEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(counselorOfficeIdList: List<CounselorOfficeIdEntity>)

    @Query("SELECT * FROM CounselorOfficeId ORDER BY EMAIL ASC")
    suspend fun getAll(): List<CounselorOfficeIdEntity>

    @Query("SELECT * FROM CounselorOfficeId WHERE EMAIL = :email")
    suspend fun getItem(email: String): CounselorOfficeIdEntity?

    @Update
    suspend fun update(counselorOfficeId: CounselorOfficeIdEntity)

    @Update
    suspend fun update(counselorOfficeIdList: List<CounselorOfficeIdEntity>)

    @Query("DELETE FROM CounselorOfficeId")
    suspend fun deleteAll()

    @Query("DELETE FROM CounselorOfficeId WHERE EMAIL = :email")
    suspend fun deleteItem(email: String)
}
