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
    override suspend fun getItem(user: FirebaseUser): UserTypeResponse? {
        val uid = user.uid
        val userTypeValues = listOf(0, 1, 2) // ??? 의미가 없다 상실
        val documentResponse = db.collection(Constants.USERS)
            .document(uid)
            .collection(Constants.USER_TYPE)
            // .whereIn(Constants.USER_TYPE, userTypeValues)
            .get()
            .await()
        return if (!documentResponse.isEmpty) {
            UserTypeResponse(documentResponse.first())
        } else {
            null
        }
    }

    override suspend fun setItem(user: FirebaseUser, type: UserTypeResponse): Boolean {
        return runCatching {
            db.collection(Constants.USERS)
                .document(user.uid)
                .collection("usertype")
                .document()
                .set(type)
                .await()
        }.fold(
            onSuccess = { true },
            onFailure = { false },
        )
    }
}
