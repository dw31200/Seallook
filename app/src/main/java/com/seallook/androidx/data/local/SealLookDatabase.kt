package com.seallook.androidx.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seallook.androidx.data.local.model.CounselingTypeEntity

@Database(entities = [CounselingTypeEntity::class], version = 1)
abstract class SealLookDatabase : RoomDatabase() {
    abstract fun counselingTypeDao(): CounselingTypeDao
}
