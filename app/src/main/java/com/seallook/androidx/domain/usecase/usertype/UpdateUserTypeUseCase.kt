package com.seallook.androidx.domain.usecase.usertype

import com.seallook.androidx.data.repository.usertype.UserTypeRepository
import com.seallook.androidx.domain.model.UserTypeModel
import com.seallook.androidx.share.UserTypeOption
import dagger.Reusable
import javax.inject.Inject

@Reusable
class UpdateUserTypeUseCase @Inject constructor(
    private val userTypeRepository: UserTypeRepository,
) {
    suspend operator fun invoke(params: Params): Result<Unit> {
        if (params.email == null || params.userType == null) return Result.failure(Error("email or userType is null"))
        return runCatching {
            userTypeRepository.update(
                UserTypeModel(
                    params.email,
                    UserTypeOption.values()[params.userType],
                ).toDataModel(),
            )
        }
    }

    data class Params(
        val email: String?,
        val userType: Int?,
    )
}
