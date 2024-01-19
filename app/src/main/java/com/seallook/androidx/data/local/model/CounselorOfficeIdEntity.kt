package com.seallook.androidx.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("CounselorOfficeId")
data class CounselorOfficeIdEntity(
    @PrimaryKey
    val email: String,
    val officeId: String,
)
