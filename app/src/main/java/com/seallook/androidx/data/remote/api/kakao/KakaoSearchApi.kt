package com.seallook.androidx.data.remote.api.kakao

import com.seallook.androidx.data.remote.model.KakaoSearchListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoSearchApi {
    @GET("v2/local/search/keyword.json")
    suspend fun getList(
        @Header("Authorization") id: String = KakaoApiKey.REST_API_KEY,
        @Query("query") query: String,
    ): KakaoSearchListResponse
}
