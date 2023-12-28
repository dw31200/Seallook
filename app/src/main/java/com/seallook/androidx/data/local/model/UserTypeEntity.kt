package com.seallook.androidx.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seallook.androidx.share.UserTypeOption

@Entity("UserType")
data class UserTypeEntity(
    @PrimaryKey
    val email: String,
    val userTypeOption: UserTypeOption,
)
