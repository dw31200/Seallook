package com.seallook.androidx.data.local.model

import androidx.room.Entity
import java.time.DayOfWeek

@Entity("CounselingSchedule", primaryKeys = ["id", "email"])
data class CounselingScheduleEntity(
    val id: String,
    val email: String,
    val title: String,
    val repeatedDay: DayOfWeek,
    val time: String,
    val currentTime: Int,
    val clientCount: Int,
    val price: Int,
)
