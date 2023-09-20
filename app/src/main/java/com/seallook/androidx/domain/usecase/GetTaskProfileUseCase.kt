package com.seallook.androidx.domain.usecase

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.seallook.androidx.data.repository.GetTaskProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskProfileUseCase @Inject constructor(
    private val getTaskProfileRepository: GetTaskProfileRepository,
) {
    operator fun invoke(): Flow<Task<DocumentSnapshot>> {
        return getTaskProfileRepository.getProfile()
    }
}
