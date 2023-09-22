package com.seallook.androidx.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seallook.androidx.data.local.model.CounselingType

@Database(entities = [CounselingType::class], version = 1)
abstract class SealLookDatabase : RoomDatabase() {
    abstract fun counselingTypeDao(): CounselingTypeDao
}
