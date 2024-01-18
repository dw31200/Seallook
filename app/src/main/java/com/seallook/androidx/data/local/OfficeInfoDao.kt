package com.seallook.androidx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.seallook.androidx.data.local.model.OfficeInfoEntity

@Dao
interface OfficeInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(officeInfo: OfficeInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(officeInfoList: List<OfficeInfoEntity>)

    @Query("SELECT * FROM OfficeInfo ORDER BY ID ASC")
    suspend fun getAll(): List<OfficeInfoEntity>

    @Query("SELECT * FROM OfficeInfo WHERE ID = :id")
    suspend fun getItem(id: String): OfficeInfoEntity?

    @Update
    suspend fun update(officeInfo: OfficeInfoEntity)

    @Update
    suspend fun update(officeInfoList: List<OfficeInfoEntity>)

    @Query("DELETE FROM OfficeInfo")
    suspend fun deleteAll()

    @Query("DELETE FROM OfficeInfo WHERE ID = :id")
    suspend fun deleteItem(id: String)
}
