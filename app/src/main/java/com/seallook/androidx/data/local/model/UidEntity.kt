package com.seallook.androidx.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seallook.androidx.base.LocalModel

@Entity("Uid")
data class UidEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val uid: String,
) : LocalModel
