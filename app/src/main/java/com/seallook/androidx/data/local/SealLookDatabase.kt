package com.seallook.androidx.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.seallook.androidx.data.local.model.CounselingTypeEntity
import com.seallook.androidx.data.local.model.OfficeInfoEntity

@Database(entities = [CounselingTypeEntity::class, OfficeInfoEntity::class], version = 1)
abstract class SealLookDatabase : RoomDatabase() {
    abstract fun counselingTypeDao(): CounselingTypeDao

    abstract fun officeInfoDao(): OfficeInfoDao
}
