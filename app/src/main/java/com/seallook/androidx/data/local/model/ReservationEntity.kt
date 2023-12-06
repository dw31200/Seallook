package com.seallook.androidx.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("Reservation")
data class ReservationEntity(
    @PrimaryKey
    val id: String,
    val counselorEmail: String,
    val scheduleId: String,
    val clientEmail: String,
    val date: Date,
    val confirm: Boolean,
)
