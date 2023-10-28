package com.seallook.androidx.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seallook.androidx.base.LocalModel
import java.util.Date

@Entity("CounselingSchedule")
data class CounselingScheduleEntity(
    @PrimaryKey
    val id: Int,
    val date: Date,
    val typeId: Int,
) : LocalModel
