package com.seallook.androidx.data.local.model

import androidx.room.TypeConverter
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun localDateToDate(localDate: LocalDate?): Date? {
        return java.sql.Date.valueOf(localDate.toString())
    }

    @TypeConverter
    fun repeatedDayToInt(repeatedDay: DayOfWeek): Int {
        return repeatedDay.ordinal
    }

    @TypeConverter
    fun intToRepeatedDay(int: Int): DayOfWeek {
        return DayOfWeek.values()[int]
    }
}
