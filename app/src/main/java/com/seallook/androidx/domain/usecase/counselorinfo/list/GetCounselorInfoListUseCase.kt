package com.seallook.androidx.domain.usecase.counselorinfo.list

import com.seallook.androidx.data.repository.counselor.list.CounselorListRepository
import com.seallook.androidx.domain.model.CounselorListModel
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetCounselorInfoListUseCase @Inject constructor(
    private val counselorListRepository: CounselorListRepository,
) {
    suspend operator fun invoke(): List<CounselorListModel> {
        return counselorListRepository.getAll().map {
            CounselorListModel(it)
        }
    }
}
