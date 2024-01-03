package com.seallook.androidx.data.repository.kakao

import com.seallook.androidx.data.model.KakaoSearch
import com.seallook.androidx.data.remote.api.kakao.KakaoSearchApiService
import javax.inject.Inject

class KakaoSearchRepositoryImpl @Inject constructor(
    private val kakaoSearchApiService: KakaoSearchApiService,
) : KakaoSearchRepository {
    override suspend fun getList(query: String, x: String, y: String): List<KakaoSearch> {
        return kakaoSearchApiService.getList(
            query = query,
            x = x,
            y = y,
        )
            .documents
            .map {
                KakaoSearch(it)
            }
    }
}
