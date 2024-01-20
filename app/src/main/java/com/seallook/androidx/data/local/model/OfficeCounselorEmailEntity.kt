package com.seallook.androidx.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("OfficeCounselorEmail")
data class OfficeCounselorEmailEntity(
    @PrimaryKey
    val id: String,
    val officeId: String,
    val counselorEmail: String,
)
