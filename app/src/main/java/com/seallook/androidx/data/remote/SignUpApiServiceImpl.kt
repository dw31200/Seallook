package com.seallook.androidx.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.seallook.androidx.data.remote.model.ProfileResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignUpApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
) : SignUpApiService {
    override suspend fun signUp(profile: ProfileResponse, password: String?): Exception? {
        return withContext(Dispatchers.IO) {
            val uid = if (auth.currentUser == null) {
                try {
                    val result =
                        auth.createUserWithEmailAndPassword(profile.email, password!!).await()
                    result.user?.uid
                } catch (e: Exception) {
                    return@withContext e
                }
            } else {
                auth.uid
            }

            assert(uid != null)

            db.collection(Constants.USERS)
                .document(uid!!)
                .set(profile)
                .await()

            return@withContext null
        }
    }
}
