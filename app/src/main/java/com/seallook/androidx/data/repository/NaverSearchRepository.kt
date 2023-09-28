package com.seallook.androidx.data.repository

import com.seallook.androidx.data.model.NaverSearchInfo
import kotlinx.coroutines.flow.Flow

interface NaverSearchRepository {
    fun getNaverSearchResponse(type: String, query: String): Flow<List<NaverSearchInfo>>
}