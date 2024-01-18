package com.seallook.androidx.data.remote.api.kakao

import com.google.android.play.integrity.internal.x
import com.google.android.play.integrity.internal.y
import com.seallook.androidx.data.remote.model.KakaoSearchListResponse
import javax.inject.Inject

class KakaoSearchApiServiceImpl @Inject constructor(
    private val kakaoSearchApi: KakaoSearchApi,
) : KakaoSearchApiService {
    override suspend fun getList(query: String, x: String, y: String): KakaoSearchListResponse {
        return kakaoSearchApi.getList(
            query = query,
            x = x,
            y = y,
        )
    }

    override suspend fun getList(query: String): KakaoSearchListResponse {
        return kakaoSearchApi.getList(
            query = query,
        )
    }
}
