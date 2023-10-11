package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.NaverSearchInfo
import kotlinx.coroutines.flow.Flow

interface NaverSearchRepository {
    suspend fun getNaverSearchResponse(type: String, query: String): List<NaverSearchInfo>?
}
