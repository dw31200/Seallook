package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.ProfileResponse
import com.seallook.androidx.share.Constants
import javax.inject.Inject

class SetProfileApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
) : SetProfileApiService {
    override suspend fun setProfile(profile: ProfileResponse) {
        db.collection(Constants.USERS)
            .document(auth.currentUser?.uid!!)
            .set(profile)
    }
}
