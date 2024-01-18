package com.seallook.androidx.data.remote.api.kakao

import com.seallook.androidx.data.remote.model.KakaoSearchListResponse

interface KakaoSearchApiService {
    suspend fun getList(query: String, x: String, y: String): KakaoSearchListResponse

    suspend fun getList(query: String): KakaoSearchListResponse
}
