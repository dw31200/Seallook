package com.seallook.androidx.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("Profile")
data class ProfileEntity(
    @PrimaryKey
    val id: Int,
    val email: String,
    val name: String,
    val gender: Int,
    val birth: Date,
    val timestamp: Date,
    val userType: Int,
)
