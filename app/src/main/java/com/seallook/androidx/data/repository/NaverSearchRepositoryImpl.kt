package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.NaverSearchInfo
import com.seallook.androidx.data.remote.naver.NaverSearchApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NaverSearchRepositoryImpl @Inject constructor(
    private val naverSearchApiService: NaverSearchApiService,
) : NaverSearchRepository {
    override fun getNaverSearchResponse(type: String, query: String): Flow<List<NaverSearchInfo>> {
        return flow {
            val remote = naverSearchApiService.getNaverSearchResponse(type, query).map {
                NaverSearchInfo(it)
            }
            emit(remote)
        }
    }
}
