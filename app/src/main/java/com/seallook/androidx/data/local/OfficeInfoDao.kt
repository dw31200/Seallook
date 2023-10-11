package com.seallook.androidx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seallook.androidx.data.local.model.OfficeInfoEntity

@Dao
interface OfficeInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setInfo(officeInfoEntity: OfficeInfoEntity)

    @Query("SELECT * FROM officeInfoEntity WHERE id = :id")
    suspend fun getInfo(id: Int): OfficeInfoEntity?
}
