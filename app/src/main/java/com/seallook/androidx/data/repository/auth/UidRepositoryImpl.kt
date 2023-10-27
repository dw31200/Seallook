package com.seallook.androidx.data.repository.auth

import com.seallook.androidx.data.local.UidDao
import com.seallook.androidx.data.model.Uid
import javax.inject.Inject

class UidRepositoryImpl @Inject constructor(
    private val uidDao: UidDao,
) : UidRepository {
    override suspend fun getItem(id: Int): Uid? {
        return uidDao.getItem(id)?.let { Uid(it) }
    }

    override suspend fun insert(uid: Uid) {
        uidDao.insert(uid.toLocalModel())
    }
}
