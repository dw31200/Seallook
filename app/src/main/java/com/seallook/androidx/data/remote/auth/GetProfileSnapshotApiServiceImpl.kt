package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.seallook.androidx.data.remote.model.ProfileResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProfileSnapshotApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
) : GetProfileSnapshotApiService {
    override fun getProfile(): Flow<ProfileResponse?> {
        val currentUser = auth.currentUser
        val uid = currentUser?.uid ?: return flow { emit(null) }
        return db.collection(Constants.USERS).document(uid).snapshots().map {
            if (it.exists()) {
                return@map ProfileResponse(it)
            } else {
                return@map null
            }
        }
    }
}
