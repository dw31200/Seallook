package com.seallook.androidx.domain.usecase.counselorinfo.counselingtype

import com.seallook.androidx.domain.model.CounselingTypeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CombineCounselingTypeUseCase @Inject constructor(
    private val getCounselingTypeRemoteUseCase: GetCounselingTypeRemoteUseCase,
    private val getCounselingTypeLocalUseCase: GetCounselingTypeLocalUseCase,
    private val setCounselingTypeUseCase: SetCounselingTypeUseCase,
) {
    operator fun invoke(uid: String): Flow<List<CounselingTypeModel>> {
        return flow {
            val local = getCounselingTypeLocalUseCase()
            emit(local)
            val remote = getCounselingTypeRemoteUseCase(uid)
            setCounselingTypeUseCase(remote)
            emit(remote)
        }
    }
}
