package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.NaverSearchInfo
import com.seallook.androidx.data.remote.naver.NaverSearchApiService
import javax.inject.Inject

class NaverSearchRepositoryImpl @Inject constructor(
    private val naverSearchApiService: NaverSearchApiService,
) : NaverSearchRepository {
    override suspend fun getNaverSearchResponse(type: String, query: String): List<NaverSearchInfo>? {
        return naverSearchApiService.getNaverSearchResponse(type, query).map {
            NaverSearchInfo(it)
        }
    }
}
