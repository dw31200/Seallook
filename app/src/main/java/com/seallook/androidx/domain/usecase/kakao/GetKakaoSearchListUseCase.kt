package com.seallook.androidx.domain.usecase.kakao

import com.seallook.androidx.data.repository.kakao.KakaoSearchRepository
import com.seallook.androidx.domain.model.KakaoSearchModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetKakaoSearchListUseCase @Inject constructor(
    private val kakaoSearchRepository: KakaoSearchRepository,
) {
    suspend operator fun invoke(query: String): List<KakaoSearchModel> {
        return kakaoSearchRepository.getList(query = query).map {
            KakaoSearchModel(it)
        }
    }
}
