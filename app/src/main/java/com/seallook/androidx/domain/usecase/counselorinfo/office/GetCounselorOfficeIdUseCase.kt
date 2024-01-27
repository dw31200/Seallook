package com.seallook.androidx.domain.usecase.counselorinfo.office

import com.seallook.androidx.data.repository.counselor.office.CounselorOfficeIdRepository
import com.seallook.androidx.domain.model.CounselorOfficeIdModel
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetCounselorOfficeIdUseCase @Inject constructor(
    private val counselorOfficeIdRepository: CounselorOfficeIdRepository,
) {
    operator fun invoke(params: Params): Flow<CounselorOfficeIdModel?> {
        return counselorOfficeIdRepository.getItem(params.email).map {
            it?.let {
                CounselorOfficeIdModel(it)
            }
        }
    }

    data class Params(
        val _email: String?,
    ) {
        val email: String
            get() = _email ?: throw IllegalStateException("email is null")
    }
}
