package com.seallook.androidx.domain.usecase

import com.seallook.androidx.data.repository.NaverSearchRepository
import com.seallook.androidx.domain.model.NaverSearchModel
import dagger.Reusable
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetNaverSearchUseCase @Inject constructor(
    private val naverSearchRepository: NaverSearchRepository,
) {
    suspend operator fun invoke(type: String, query: String): List<NaverSearchModel>? {
        return naverSearchRepository.getNaverSearchResponse(type, query)?.let {
            it.map {
                NaverSearchModel(it)
            }
        }
    }
}
