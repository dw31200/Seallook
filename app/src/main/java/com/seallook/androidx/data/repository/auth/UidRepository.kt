package com.seallook.androidx.data.repository.auth

import com.seallook.androidx.data.model.Uid

interface UidRepository {
    suspend fun getItem(id: Int): Uid?

    suspend fun insert(uid: Uid)
}
