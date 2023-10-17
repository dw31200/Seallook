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
//    TODO entity를 하나로 합치고 entity에서 컨버터로 필드에 타입 지정하는 방식은 괜찮을까요?
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

    abstract fun reservedItemDao(): ReservedItemDao

    abstract fun counselingScheduleDao(): CounselingScheduleDao
}
