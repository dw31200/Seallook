package com.seallook.androidx.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("CounselorInfo")
data class CounselorInfoEntity(
    @PrimaryKey
    val email: String,
    val name: String,
    val description: String,
    val thumbnail: String,
)
