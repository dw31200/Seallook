package com.seallook.androidx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.seallook.androidx.data.local.model.UserTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userType: UserTypeEntity)

    @Query("SELECT * FROM UserType ORDER BY EMAIL ASC")
    fun get(): Flow<UserTypeEntity?>

    @Update
    suspend fun update(userType: UserTypeEntity)

    @Query("DELETE FROM UserType")
    suspend fun deleteAll()
}
