package com.seallook.androidx.data.repository.usertype

import com.seallook.androidx.data.local.UserTypeDao
import com.seallook.androidx.data.model.UserType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserTypeRepositoryImpl @Inject constructor(
    private val userTypeDao: UserTypeDao,
) : UserTypeRepository {
    override fun get(): Flow<UserType?> {
        return userTypeDao.get().map {
            it?.let {
                UserType(it)
            }
        }
    }

    override suspend fun update(userType: UserType) {
        userTypeDao.deleteAll()
        userTypeDao.insert(userType.toLocalModel())
    }
}
