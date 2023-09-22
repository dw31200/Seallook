package com.seallook.androidx.data.remote.naver

import com.seallook.androidx.data.remote.model.NaverSearchApiResponse

interface NaverSearchApiService {
    suspend fun getNaverSearchResponse(type: String, query: String): List<NaverSearchApiResponse>
}
