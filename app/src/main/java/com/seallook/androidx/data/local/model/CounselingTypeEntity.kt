package com.seallook.androidx.data.local.model

import androidx.room.Entity

@Entity("CounselingType", primaryKeys = ["id", "email"])
data class CounselingTypeEntity(
    val id: String,
    val email: String,
    val title: String,
    val clientCount: Int,
    val time: Int,
    val price: Int,
)
