package com.seallook.androidx.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("reservedItemEntity")
data class ReservedItemEntity(
    @PrimaryKey
    val id: Int,
    val scheduleId: Int,
    val clientUid: String,
)
