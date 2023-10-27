package com.seallook.androidx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.seallook.androidx.data.local.model.UidEntity

@Dao
interface UidDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(uid: UidEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(uidList: List<UidEntity>)

    @Query("SELECT * FROM Uid ORDER BY ID ASC")
    suspend fun getAll(): List<UidEntity>

    @Query("SELECT * FROM Uid WHERE ID = :id")
    suspend fun getItem(id: Int): UidEntity?

    @Update
    suspend fun update(uid: UidEntity)

    @Update
    suspend fun update(uidList: List<UidEntity>)

    @Query("DELETE FROM Uid")
    suspend fun deleteAll()

    @Query("DELETE FROM Uid WHERE ID = :id")
    suspend fun deleteItem(id: Int)
}
