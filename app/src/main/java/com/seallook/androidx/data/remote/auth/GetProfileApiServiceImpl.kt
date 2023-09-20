package com.seallook.androidx.data.remote.auth

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.share.Constants
import javax.inject.Inject

class GetProfileApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
) : GetProfileApiService {
    override suspend fun getProfile(): Task<DocumentSnapshot> {
        val user = auth.currentUser
        val uid = user?.uid
        return uid?.let { db.collection(Constants.USERS).document(it).get() } ?: Tasks.forResult(null)
    }
}
