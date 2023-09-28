package com.seallook.androidx.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("counselingTypeEntity")
data class CounselingTypeEntity(
    @PrimaryKey
    val id: Int,
    val uid: String,
    val title: String,
    val count: Int,
    val time: Int,
    val pay: Int,
)
