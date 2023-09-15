package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseAuth
import com.seallook.androidx.data.remote.model.ProfileResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignUpApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
) : SignUpApiService {
    override suspend fun signUp(profile: ProfileResponse, password: String?): Exception? =
        withContext(Dispatchers.IO) {
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

            return@withContext null
        }
}
