package com.seallook.androidx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.seallook.androidx.data.local.model.ReservedItemEntity

@Dao
interface ReservedItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(reservedItemEntity: ReservedItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(reservedItemEntityList: List<ReservedItemEntity>)

    @Query("SELECT * FROM reservedItemEntity ORDER BY ID ASC")
    suspend fun getAll(): List<ReservedItemEntity>

    @Update
    suspend fun updateItem(reservedItemEntity: ReservedItemEntity)

    @Query("DELETE FROM reservedItemEntity WHERE ID = :id")
    suspend fun deleteItem(id: Int)
}
