package com.seallook.androidx.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Reservation")
data class ReservationEntity(
    @PrimaryKey
    val id: Int,
    val scheduleId: Int,
    val clientUid: String,
)
