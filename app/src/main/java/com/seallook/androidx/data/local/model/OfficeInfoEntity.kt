package com.seallook.androidx.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("OfficeInfo")
data class OfficeInfoEntity(
    @PrimaryKey
    val id: String,
    val place_name: String,
    val phone: String,
    val address_name: String,
    val road_address_name: String,
    val x: String,
    val y: String,
    val place_url: String,
    val distance: String,
)
