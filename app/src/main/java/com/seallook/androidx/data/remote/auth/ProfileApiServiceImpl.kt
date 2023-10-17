package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.ProfileResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : ProfileApiService {
    override suspend fun getItem(user: FirebaseUser?): ProfileResponse? {
        val uid = user?.uid ?: return null
        val documentResponse = db.collection(Constants.USERS).document(uid).get().await()
        return if (documentResponse.exists()) {
            ProfileResponse(documentResponse)
        } else {
            null
        }
    }

    override suspend fun setItem(user: FirebaseUser?, profile: ProfileResponse) {
        user?.uid?.let {
            db.collection(Constants.USERS)
                .document(it)
                .set(profile)
        }
    }
}
