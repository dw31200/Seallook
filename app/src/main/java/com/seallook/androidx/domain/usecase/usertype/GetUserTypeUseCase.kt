package com.seallook.androidx.domain.usecase.usertype

import com.seallook.androidx.data.repository.usertype.UserTypeRepository
import com.seallook.androidx.domain.model.UserTypeModel
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetUserTypeUseCase @Inject constructor(
    private val userTypeRepository: UserTypeRepository,
) {
    operator fun invoke(): Flow<UserTypeModel?> {
        return userTypeRepository.get().map {
            it?.let {
                UserTypeModel(it)
            }
        }
    }
}
