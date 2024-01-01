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
import com.seallook.androidx.data.local.model.UidEntity
import com.seallook.androidx.data.local.model.UserTypeEntity

@Database(
    entities = [
        CounselingTypeEntity::class,
        OfficeInfoEntity::class,
        CounselingScheduleEntity::class,
        ReservationEntity::class,
        CounselorInfoEntity::class,
        ProfileEntity::class,
        UidEntity::class,
        UserTypeEntity::class,
    ],
    version = 4,
)
@TypeConverters(Converters::class)
abstract class SealLookDatabase : RoomDatabase() {
    abstract fun counselingTypeDao(): CounselingTypeDao

    abstract fun officeInfoDao(): OfficeInfoDao

    abstract fun reservationDao(): ReservationDao

    abstract fun counselingScheduleDao(): CounselingScheduleDao

    abstract fun counselorInfoDao(): CounselorInfoDao

    abstract fun profileDao(): ProfileDao

    abstract fun uidDao(): UidDao

    abstract fun userDao(): UserTypeDao
}
