package com.seallook.androidx.data.remote.naver

import com.seallook.androidx.data.remote.model.NaverSearchApiResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface NaverSearchApi {
    @GET("search/{type}")
    suspend fun getNaverSearchResponse(
        @Header("X-Naver-Client-Id") id: String = NaverApiKey.NAVER_CLIENT_ID,
        @Header("X-Naver-Client-Secret") pw: String = NaverApiKey.NAVER_CLIENT_SECRET,
        @Path("type") type: String,
        @Query("query") query: String,
        @Query("display") display: Int = 5,
    ): List<NaverSearchApiResponse>
}
