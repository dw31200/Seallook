package com.seallook.androidx.data.remote

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.seallook.androidx.data.remote.model.CounselingTypeListResponse
import com.seallook.androidx.data.remote.model.CounselingTypeResponse
import com.seallook.androidx.data.remote.model.CounselorInfoResponse
import com.seallook.androidx.data.remote.model.OfficeInfoResponse
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
        val documentResponse = db.collection(Constants.COUNSELORS)
            .document(uid)
            .collection("counselorinfo")
            .document(uid)
            .get().await()
        return if (documentResponse.exists()) {
            CounselorInfoResponse(documentResponse)
        } else {
            null
        }
    }

    override suspend fun setCounselorInfo(user: FirebaseUser?, info: CounselorInfoResponse): Boolean? {
        return user?.uid?.let {
            runCatching {
                db.collection(Constants.COUNSELORS)
                    .document(it)
                    .collection("counselorinfo")
                    .document(it)
                    .set(info)
            }.fold(
                onSuccess = { true },
                onFailure = { false },
            )
        }
    }

    override suspend fun getCounselingType(user: FirebaseUser?): List<CounselingTypeResponse>? {
        val uid = user?.uid ?: return listOf()
        val list = mutableListOf<CounselingTypeResponse>()
        val documentResponse = db.collection(Constants.COUNSELORS)
            .document(uid)
            .collection("counselingType")
            .document(uid)
            .get().await()
        return if (documentResponse.exists()) {
            val result = Gson().fromJson(documentResponse.getString("counselingList"), List::class.java)
            for (i in 0 until result.size) {
                list.add(Gson().fromJson(result[i].toString(), CounselingTypeResponse::class.java))
            }
            list
        } else {
            null
        }
    }

    override suspend fun updateCounselingType(user: FirebaseUser?, type: CounselingTypeListResponse) {
        user?.uid?.let {
            db.collection(Constants.COUNSELORS)
                .document(it)
                .collection("counselingType")
                .document(it)
                .set(type)
        }
    }

    override suspend fun getOfficeInfo(user: FirebaseUser?): OfficeInfoResponse? {
        val uid = user?.uid ?: return null
        val documentResponse = db.collection(Constants.COUNSELORS)
            .document(uid)
            .collection("officeinfo")
            .document(uid)
            .get().await()
        return if (documentResponse.exists()) {
            OfficeInfoResponse(documentResponse)
        } else {
            null
        }
    }

    override suspend fun setOfficeInfo(user: FirebaseUser?, info: OfficeInfoResponse) {
        user?.uid?.let {
            db.collection(Constants.COUNSELORS)
                .document(it)
                .collection("officeinfo")
                .document(it)
                .set(info)
        }
    }
}
