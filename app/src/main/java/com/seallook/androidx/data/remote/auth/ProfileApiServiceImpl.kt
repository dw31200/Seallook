package com.seallook.androidx.data.remote.auth

import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.ProfileResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : ProfileApiService {
    override suspend fun getItem(uid: String): ProfileResponse? {
        val documentResponse = db
            .collection(Constants.USERS)
            .document(uid)
            .get()
            .await()
        return if (documentResponse.exists()) {
            ProfileResponse(documentResponse)
        } else {
            null
        }
    }

    override suspend fun getWithEmail(email: String): List<ProfileResponse> {
        val documentResponse = db
            .collection(Constants.USERS)
            .whereEqualTo("email", email)
            .get()
            .await()
        return if (!documentResponse.isEmpty) {
            ProfileResponse(documentResponse)
        } else {
            emptyList()
        }
    }

    override suspend fun setItem(uid: String, profile: ProfileResponse) {
        db.collection(Constants.USERS)
            .document(uid)
            .set(profile)
            .await()
    }
}
