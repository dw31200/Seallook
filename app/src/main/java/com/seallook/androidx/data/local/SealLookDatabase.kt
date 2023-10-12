package com.seallook.androidx.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.seallook.androidx.data.local.model.Converters
import com.seallook.androidx.data.local.model.CounselingScheduleEntity
import com.seallook.androidx.data.local.model.CounselingTypeEntity
import com.seallook.androidx.data.local.model.OfficeInfoEntity
import com.seallook.androidx.data.local.model.ReservedItemEntity

@Database(
    entities = [
        CounselingTypeEntity::class,
        OfficeInfoEntity::class,
        CounselingScheduleEntity::class,
        ReservedItemEntity::class,
    ],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class SealLookDatabase : RoomDatabase() {
    abstract fun counselingTypeDao(): CounselingTypeDao

    abstract fun officeInfoDao(): OfficeInfoDao
}
