package com.seallook.androidx.data.repository.counselor.list

import com.seallook.androidx.data.model.CounselorList
import com.seallook.androidx.data.remote.counselor.list.CounselorListApiService
import javax.inject.Inject

class CounselorListRepositoryImpl @Inject constructor(
    private val counselorListApiService: CounselorListApiService,
) : CounselorListRepository {
    override suspend fun getAll(): List<CounselorList> {
        return counselorListApiService.getAll().map {
            CounselorList(it)
        }
    }
}
