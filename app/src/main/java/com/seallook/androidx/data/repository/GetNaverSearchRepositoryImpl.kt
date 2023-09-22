package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.NaverSearchInfo
import com.seallook.androidx.data.remote.naver.NaverSearchApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNaverSearchRepositoryImpl @Inject constructor(
    private val getNaverSearchApiService: NaverSearchApiService,
) : GetNaverSearchRepository {
    override fun getNaverSearchResponse(type: String, query: String): Flow<List<NaverSearchInfo>> {
        return flow {
            val remote = getNaverSearchApiService.getNaverSearchResponse(type, query).map {
                NaverSearchInfo(it)
            }
            emit(remote)
        }
    }
}
