package com.seallook.androidx.data.repository

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.local.CounselingTypeDao
import com.seallook.androidx.data.model.CounselingType
import com.seallook.androidx.data.remote.FirebaseFirestoreApiService
import com.seallook.androidx.data.remote.model.CounselingTypeListResponse
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CounselingTypeRepositoryImpl @Inject constructor(
    private val counselingTypeDao: CounselingTypeDao,
    private val firebaseFirestoreApiService: FirebaseFirestoreApiService,
) : CounselingTypeRepository {
    override suspend fun setCounselingType(counselingType: CounselingType) {
        counselingTypeDao.insert(counselingType.toEntity())
    }

    override suspend fun deleteCounselingType(counselingTypeId: Int) {
        counselingTypeDao.deleteItem(counselingTypeId)
    }

    override suspend fun getCounselingType(): List<CounselingType> {
        return counselingTypeDao.getAll().map {
            CounselingType(it)
        }
    }

    override suspend fun initCounselingType(user: FirebaseUser?): List<CounselingType>? {
        return firebaseFirestoreApiService.getCounselingType(user)?.let {
            it.map {
                CounselingType(it)
            }
        }
    }

    override suspend fun updateCounselingType(user: FirebaseUser?, type: List<CounselingType>) {
        firebaseFirestoreApiService.updateCounselingType(user, CounselingTypeListResponse(type.map { it.toResponse() }))
    }
}
