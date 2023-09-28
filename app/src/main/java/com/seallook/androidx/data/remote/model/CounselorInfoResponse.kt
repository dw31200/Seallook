package com.seallook.androidx.data.remote.model

data class CounselorInfoResponse(
    val counselorId: String,
    val name: String,
    val createdAt: Long,
    val description: String,
    val counselingType: CounselingTypeResponse,
    val imageUrl: String,
)
