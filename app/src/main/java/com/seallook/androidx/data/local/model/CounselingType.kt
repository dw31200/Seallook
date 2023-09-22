package com.seallook.androidx.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CounselingType(
    @PrimaryKey
    val id: Int,
    val title: String,
    val count: Int,
    val time: Int,
    val pay: Int,
)
