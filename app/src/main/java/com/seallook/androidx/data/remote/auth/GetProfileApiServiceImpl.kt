package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.ProfileResponse
import com.seallook.androidx.share.Constants
import java.util.Date
import javax.inject.Inject

class GetProfileApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
) : GetProfileApiService {
    override suspend fun getProfile(): ProfileResponse? {
        val user = auth.currentUser
        val uid = user?.uid ?: return null
        return runCatching {
            db.collection(Constants.USERS).document(uid).get().result
        }.fold(
            onSuccess = {
                ProfileResponse(it)
            },
            onFailure = {
                ProfileResponse(
                    "",
                    user.email ?: "",
                    user.displayName ?: "",
                    0,
                    Date(),
                    Date(),
                )
            },
        )
    }
}
