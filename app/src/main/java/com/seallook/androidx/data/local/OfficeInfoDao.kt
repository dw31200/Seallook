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
    suspend fun insertItem(officeInfoEntity: OfficeInfoEntity)

    @Query("SELECT * FROM officeInfoEntity WHERE id = :id")
    suspend fun getItem(id: Int): OfficeInfoEntity?

    @Update
    suspend fun updateItem(officeInfoEntity: OfficeInfoEntity)

    @Query("DELETE FROM officeInfoEntity WHERE ID = :id")
    suspend fun deleteItem(id: Int)
}
