package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.UserTypeResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserTypeApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : UserTypeApiService {
    override suspend fun getItem(user: FirebaseUser?): UserTypeResponse? {
        val uid = user?.uid ?: return null
        val userTypeValues = listOf(0, 1, 2)
        val documentResponse = db.collection(Constants.USERS)
            .document(uid)
            .collection(Constants.USER_TYPE)
            .whereIn(Constants.USER_TYPE, userTypeValues)
            .get().await()
        return if (!documentResponse.isEmpty) {
            UserTypeResponse(documentResponse.documents[0])
        } else {
            null
        }
    }

    override suspend fun setItem(user: FirebaseUser?, type: UserTypeResponse): Boolean? {
        return user?.uid?.let {
            runCatching {
                db.collection(Constants.USERS)
                    .document(it)
                    .collection("usertype")
                    .document()
                    .set(type)
            }.fold(
                onSuccess = { true },
                onFailure = { false },
            )
        }
    }
}
