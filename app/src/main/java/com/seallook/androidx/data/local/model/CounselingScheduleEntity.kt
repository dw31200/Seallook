package com.seallook.androidx.data.local.model

import androidx.room.Entity
import java.util.Date

@Entity("CounselingSchedule", primaryKeys = ["id", "email"])
data class CounselingScheduleEntity(
    val id: Int,
    val email: String,
    val date: Date,
    val typeId: Int,
    val reservation: Boolean,
)
