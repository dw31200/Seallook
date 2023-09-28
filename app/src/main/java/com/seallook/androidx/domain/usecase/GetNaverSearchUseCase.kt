package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.NaverSearchRepository
import com.seallook.androidx.domain.model.NaverSearchModel
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetNaverSearchUseCase @Inject constructor(
    private val naverSearchRepository: NaverSearchRepository,
) {
    operator fun invoke(type: String, query: String): Flow<List<NaverSearchModel>> {
        return naverSearchRepository.getNaverSearchResponse(type, query).map {
            it.map {
                NaverSearchModel(it)
            }
        }
    }
}
