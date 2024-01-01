package com.seallook.androidx.data.remote.api.kakao

import com.seallook.androidx.data.remote.model.KakaoSearchListResponse
import javax.inject.Inject

class KakaoSearchApiServiceImpl @Inject constructor(
    private val kakaoSearchApi: KakaoSearchApi,
) : KakaoSearchApiService {
    override suspend fun getList(query: String): KakaoSearchListResponse {
        return kakaoSearchApi.getList(query = query)
    }
}
