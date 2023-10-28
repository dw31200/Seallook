package com.seallook.androidx.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seallook.androidx.base.LocalModel
import java.util.Date

@Entity("Profile")
data class ProfileEntity(
    val email: String,
    val name: String,
    val gender: Int,
    val birth: Date,
    val timestamp: Date,
    val userType: Int,
) : LocalModel {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 // or foodId: Int? = null
}
