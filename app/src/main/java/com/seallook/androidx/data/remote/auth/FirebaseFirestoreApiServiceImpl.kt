package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.CounselingTypeResponse
import com.seallook.androidx.data.remote.model.CounselorInfoResponse
import com.seallook.androidx.data.remote.model.ProfileResponse
import com.seallook.androidx.data.remote.model.UserTypeResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseFirestoreApiServiceImpl @Inject constructor(
    private val db: FirebaseFirestore,
) : FirebaseFirestoreApiService {
    override suspend fun getProfile(user: FirebaseUser?): ProfileResponse? {
        val uid = user?.uid ?: return null
        val documentResponse = db.collection(Constants.USERS).document(uid).get().await()
        return if (documentResponse.exists()) {
            ProfileResponse(documentResponse)
        } else {
            null
        }
    }

    override suspend fun getUserType(user: FirebaseUser?): UserTypeResponse? {
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

    override suspend fun getCounselorInfo(user: FirebaseUser?): CounselorInfoResponse? {
        val uid = user?.uid ?: return null
        val documentResponse = db.collection(Constants.USERS)
            .document(uid)
            .collection("counselorinfo")
            .whereEqualTo("counselorId", uid)
            .get().await()
        return if (!documentResponse.isEmpty) {
            CounselorInfoResponse(documentResponse.documents[0])
        } else {
            null
        }
    }

    override suspend fun setCounselorInfo(user: FirebaseUser?, info: CounselorInfoResponse) {
        user?.uid?.let {
            db.collection(Constants.USERS)
                .document(it)
                .collection("counselorinfo")
                .document()
                .set(info)
        }
    }

    override suspend fun getCounselingType(user: FirebaseUser?): List<CounselingTypeResponse?> {
        val uid = user?.uid ?: return listOf()
        val list = mutableListOf<CounselingTypeResponse>()
        val documentResponse = db.collection(Constants.USERS)
            .document(uid)
            .collection("counselingType")
            .whereEqualTo("uid", uid)
            .get().await()
        return if (!documentResponse.isEmpty) {
            for (i in 0 until documentResponse.documents.size) {
                list.add(CounselingTypeResponse(documentResponse.documents[i]))
            }
            list
        } else {
            listOf()
        }
    }

    override suspend fun setCounselingType(user: FirebaseUser?, type: CounselingTypeResponse) {
        user?.uid?.let {
            db.collection(Constants.USERS)
                .document(it)
                .collection("counselingType")
                .document()
                .set(type)
        }
    }
}
