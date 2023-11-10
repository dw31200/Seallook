package com.seallook.androidx.data.local.model

import androidx.room.Entity

@Entity("Reservation", primaryKeys = ["id", "counselorEmail"])
data class ReservationEntity(
    val id: Int,
    val counselorEmail: String,
    val scheduleId: Int,
    val clientEmail: String,
    val confirm: Boolean,
)
