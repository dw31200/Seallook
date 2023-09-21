package com.seallook.androidx.data.remote.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot

interface GetTaskProfileApiService {
    suspend fun getProfile(): Task<DocumentSnapshot>
}
