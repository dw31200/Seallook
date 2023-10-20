package com.seallook.androidx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.seallook.androidx.data.local.model.ProfileEntity

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profile: ProfileEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profileList: List<ProfileEntity>)

    @Query("SELECT * FROM Profile ORDER BY ID ASC")
    suspend fun getAll(): List<ProfileEntity>

    @Query("SELECT * FROM Profile WHERE id = :id")
    suspend fun getItem(id: Int): ProfileEntity?

    @Update
    suspend fun update(profile: ProfileEntity)

    @Update
    suspend fun update(profileList: List<ProfileEntity>)

    @Query("DELETE FROM Profile")
    suspend fun deleteAll()

    @Query("DELETE FROM Profile WHERE ID = :id")
    suspend fun deleteItem(id: Int)
}
