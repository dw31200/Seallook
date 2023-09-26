package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.ProfileResponse
import com.seallook.androidx.data.remote.model.UserTypeResponse
import com.seallook.androidx.share.Constants
import java.util.Date
import javax.inject.Inject

class FirebaseFirestoreApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : FirebaseFirestoreApiService {
    override suspend fun getProfile(user: FirebaseUser?): ProfileResponse? {
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

    override suspend fun getUserType(user: FirebaseUser?): UserTypeResponse? {
        val uid = user?.uid ?: return null
        val userTypeValues = listOf(0, 1, 2)
        return runCatching {
            db.collection(Constants.USERS)
                .document(uid)
                .collection(Constants.USER_TYPE)
                .whereIn(Constants.USER_TYPE, userTypeValues)
                .get()
                .result
        }.fold(
            onSuccess = {
                UserTypeResponse(it.documents[0])
            },
            onFailure = {
                UserTypeResponse(0)
            },
        )
    }

    override suspend fun setProfile(user: FirebaseUser?, profile: ProfileResponse) {
        user?.uid?.let {
            db.collection(Constants.USERS)
                .document(it)
                .set(profile)
        }
    }

    override suspend fun setUserType(user: FirebaseUser?, type: UserTypeResponse) {
        user?.uid?.let {
            db.collection(Constants.USERS)
                .document(it)
                .collection("usertype")
                .document()
                .set(type)
        }
    }
}
