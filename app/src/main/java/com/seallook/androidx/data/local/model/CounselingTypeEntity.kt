package com.seallook.androidx.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seallook.androidx.base.LocalModel

@Entity("CounselingType")
data class CounselingTypeEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val clientCount: Int,
    val time: Int,
    val price: Int,
) : LocalModel
