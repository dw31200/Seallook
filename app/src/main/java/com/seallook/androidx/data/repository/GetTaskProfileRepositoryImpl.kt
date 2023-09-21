package com.seallook.androidx.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.seallook.androidx.data.remote.auth.GetTaskProfileApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTaskProfileRepositoryImpl @Inject constructor(
    private val getTaskProfileApiService: GetTaskProfileApiService,
) : GetTaskProfileRepository {
    override fun getProfile(): Flow<Task<DocumentSnapshot>> {
        return flow {
            val profile = getTaskProfileApiService.getProfile()
            emit(profile)
        }
    }
}
