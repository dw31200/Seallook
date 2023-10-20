package com.seallook.androidx.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("OfficeInfo")
data class OfficeInfoEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val link: String,
    val category: String,
    val description: String,
    val telephone: String,
    val address: String,
    val roadAddress: String,
    val mapx: Int,
    val mapy: Int,
)
