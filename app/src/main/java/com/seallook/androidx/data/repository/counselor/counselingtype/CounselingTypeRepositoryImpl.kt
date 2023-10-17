package com.seallook.androidx.data.repository.counselor.counselingtype

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.local.CounselingTypeDao
import com.seallook.androidx.data.model.CounselingType
import com.seallook.androidx.data.remote.counselor.counselingtype.CounselingTypeApiService
import com.seallook.androidx.data.remote.model.CounselingTypeListResponse
import javax.inject.Inject

class CounselingTypeRepositoryImpl @Inject constructor(
    private val counselingTypeDao: CounselingTypeDao,
    private val counselingTypeApiService: CounselingTypeApiService,
) : CounselingTypeRepository {
    override suspend fun insertItem(counselingType: CounselingType) {
        counselingTypeDao.insertItem(counselingType.toEntity())
    }

    override suspend fun insertList(counselingTypeList: List<CounselingType>) {
        counselingTypeDao.insertList(counselingTypeList.map { it.toEntity() })
    }

    override suspend fun deleteItem(counselingTypeId: Int) {
        counselingTypeDao.deleteItem(counselingTypeId)
    }

    override suspend fun getItemList(): List<CounselingType> {
        return counselingTypeDao.getAll().map {
            CounselingType(it)
        }
    }

    override suspend fun initList(user: FirebaseUser?): List<CounselingType> {
        return counselingTypeApiService.getList(user).let {
            it.map {
                CounselingType(it)
            }
        }
    }

    override suspend fun updateList(user: FirebaseUser?, type: List<CounselingType>) {
        counselingTypeApiService.updateList(user, CounselingTypeListResponse(type.map { it.toResponse() }))
    }
}
