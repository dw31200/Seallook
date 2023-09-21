package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.UserTypeResponse
import com.seallook.androidx.share.Constants
import javax.inject.Inject

class SetUserTypeApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
) : SetUserTypeApiService {
    override suspend fun setUserType(type: UserTypeResponse) {
        auth.currentUser?.uid?.let {
            db.collection(Constants.USERS)
                .document(it)
                .collection("usertype")
                .document()
                .set(type)
        }
    }
}
