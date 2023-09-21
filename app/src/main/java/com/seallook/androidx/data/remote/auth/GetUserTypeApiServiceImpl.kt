package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.seallook.androidx.data.remote.model.UserTypeResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserTypeApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : GetUserTypeApiService {
    override fun getUserType(user: FirebaseUser?): Flow<UserTypeResponse?> {
        val uid = user?.uid ?: return flow { emit(null) }
        val userTypeValues = listOf(0, 1, 2)
        return db.collection(Constants.USERS)
            .document(uid)
            .collection(Constants.USER_TYPE)
            .whereIn(Constants.USER_TYPE, userTypeValues)
            .snapshots().map {
                return@map it.documents.map {
                    UserTypeResponse(it)
                }[0]
            }
    }
}
