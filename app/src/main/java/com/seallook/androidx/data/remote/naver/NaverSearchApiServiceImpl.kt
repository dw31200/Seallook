package com.seallook.androidx.data.remote.naver

import com.seallook.androidx.data.remote.model.NaverSearchApiResponse
import javax.inject.Inject

class NaverSearchApiServiceImpl @Inject constructor(
    private val naverSearchApi: NaverSearchApi,
) : NaverSearchApiService {
    override suspend fun getNaverSearchResponse(type: String, query: String): List<NaverSearchApiResponse> {
        return runCatching {
            naverSearchApi.getNaverSearchResponse(type = type, query = query)
        }.fold(
            onSuccess = { it.items },
            onFailure = { emptyList() },
        )
    }
}
