package com.seallook.androidx.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Uid")
data class UidEntity(
    @PrimaryKey
    val id: Int,
    val uid: String,
)
