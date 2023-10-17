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
    // 조회 키 값이 있다? -> ProfileResponse / null
    override suspend fun getItem(user: FirebaseUser): ProfileResponse? {
        val uid = user.uid
        val documentResponse = db.collection(Constants.USERS).document(uid).get().await()
        return if (documentResponse.exists()) {
            ProfileResponse(documentResponse)
        } else {
            null
        }
    }

    // 저장 할 때 키 값이 없다 -> 말이 안됨
    // 저장 할 때 키 값이 있다 -> ProfileResponse
    override suspend fun setItem(user: FirebaseUser, profile: ProfileResponse) {
        db.collection(Constants.USERS)
            .document(user.uid)
            .set(profile)
    }
}
