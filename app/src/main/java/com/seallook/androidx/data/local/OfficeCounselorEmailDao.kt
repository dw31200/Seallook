package com.seallook.androidx.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.seallook.androidx.data.local.model.OfficeCounselorEmailEntity

@Dao
interface OfficeCounselorEmailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(officeCounselorEmail: OfficeCounselorEmailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(officeCounselorEmailList: List<OfficeCounselorEmailEntity>)

    @Query("SELECT * FROM OfficeCounselorEmail ORDER BY ID ASC")
    suspend fun getAll(): List<OfficeCounselorEmailEntity>

    @Query("SELECT * FROM OfficeCounselorEmail WHERE ID = :id")
    suspend fun getItem(id: String): OfficeCounselorEmailEntity?

    @Update
    suspend fun update(officeCounselorEmail: OfficeCounselorEmailEntity)

    @Update
    suspend fun update(officeCounselorEmailList: List<OfficeCounselorEmailEntity>)

    @Query("DELETE FROM OfficeCounselorEmail")
    suspend fun deleteAll()

    @Query("DELETE FROM OfficeCounselorEmail WHERE ID = :id")
    suspend fun deleteItem(id: String)
}
