package com.seallook.androidx.data.repository.kakao

import com.seallook.androidx.data.model.KakaoSearch

interface KakaoSearchRepository {
    suspend fun getList(query: String, x: String, y: String): List<KakaoSearch>

    suspend fun getList(query: String): List<KakaoSearch>
}
