package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import java.time.DayOfWeek

data class CounselingScheduleResponse(
    val id: String,
    val email: String,
    val title: String,
    val repeatedDay: DayOfWeek,
    val time: String,
    val currentTime: Int,
    val clientCount: Int,
    val price: Int,
) {
    constructor() : this("", "", "", DayOfWeek.SUNDAY, "", 0, 0, 0)

    companion object {
        private val moshi = Moshi.Builder()
            .add(DayOfWeekAdapter())
            .build()

        operator fun invoke(snapshot: DocumentSnapshot): CounselingScheduleResponse? {
            return try {
                val json = snapshot.data
                val adapter = moshi.adapter(CounselingScheduleResponse::class.java)
                adapter.fromJsonValue(json)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}

class DayOfWeekAdapter {
    @ToJson
    fun toJson(dayOfWeek: DayOfWeek): Long {
        return dayOfWeek.value.toLong()
    }

    @FromJson
    fun fromJson(value: Long): DayOfWeek {
        return when (value.toInt()) {
            0 -> DayOfWeek.MONDAY
            1 -> DayOfWeek.TUESDAY
            2 -> DayOfWeek.WEDNESDAY
            3 -> DayOfWeek.THURSDAY
            4 -> DayOfWeek.FRIDAY
            5 -> DayOfWeek.SATURDAY
            6 -> DayOfWeek.SUNDAY
            else -> throw IllegalArgumentException("Invalid DayOfWeek value: $value")
        }
    }
}
