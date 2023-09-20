package com.seallook.androidx.data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.flow.Flow

interface GetTaskProfileRepository {
    fun getProfile(): Flow<Task<DocumentSnapshot>>
}
