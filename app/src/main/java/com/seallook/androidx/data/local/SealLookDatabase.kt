package com.seallook.androidx.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.seallook.androidx.data.local.model.Converters
import com.seallook.androidx.data.local.model.CounselingScheduleEntity
import com.seallook.androidx.data.local.model.CounselingTypeEntity
import com.seallook.androidx.data.local.model.CounselorInfoEntity
import com.seallook.androidx.data.local.model.OfficeInfoEntity
import com.seallook.androidx.data.local.model.ProfileEntity
import com.seallook.androidx.data.local.model.ReservationEntity

@Database(
    entities = [
        CounselingTypeEntity::class,
        OfficeInfoEntity::class,
        CounselingScheduleEntity::class,
        ReservationEntity::class,
        CounselorInfoEntity::class,
        ProfileEntity::class,
    ],
    version = 2,
)
@TypeConverters(Converters::class)
abstract class SealLookDatabase : RoomDatabase() {
    abstract fun counselingTypeDao(): CounselingTypeDao

    abstract fun officeInfoDao(): OfficeInfoDao

    abstract fun reservationDao(): ReservationDao

    abstract fun counselingScheduleDao(): CounselingScheduleDao

    abstract fun counselorInfoDao(): CounselorInfoDao

    abstract fun profileDao(): ProfileDao
}
