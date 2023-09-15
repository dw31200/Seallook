package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.seallook.androidx.data.remote.model.ProfileResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

class GetProfileApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
) : GetProfileApiService {
    override fun getProfile(): Flow<ProfileResponse?> {
        val user = auth.currentUser
        val uid = user?.uid ?: return flow { emit(null) }
        return db.collection(Constants.USERS).document(uid).snapshots().map {
            if (it.exists()) {
                ProfileResponse(it)
            } else {
                ProfileResponse(
                    "",
                    user.email ?: "",
                    user.displayName ?: "",
                    0,
                    Date(),
                    Date(),
                )
            }
        }
    }
}
