package com.seallook.androidx.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.seallook.androidx.data.remote.model.ProfileResponse
import com.seallook.androidx.share.Constants
import com.seallook.androidx.share.Gender
import com.seallook.androidx.share.GenderOption
import com.seallook.androidx.share.TypeOption
import com.seallook.androidx.share.UserType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

class SignInApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
) : SignInApiService {
    private val _authStateFlow by lazy {
        callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth -> trySend(auth.currentUser) }

            auth.addAuthStateListener(listener)

            awaitClose {
                auth.removeAuthStateListener(listener)
            }
        }
    }

    private val _profile by lazy {
        _authStateFlow.flatMapLatest { user ->
            val uid = user?.uid

            if (uid == null) {
                MutableStateFlow(null)
            } else {
                db.collection(Constants.USERS).document(uid).snapshots().map {
                    if (it.exists()) {
                        return@map ProfileResponse(it)
                    } else {
                        return@map ProfileResponse(
                            "",
                            user.email ?: "",
                            "",
                            user.displayName ?: "",
                            GenderOption(Gender.FEMALE),
                            Date(),
                            TypeOption(UserType.CLIENT),
                            Date(),
                        )
                    }
                }
            }
        }
    }
    override fun getProfile() = _profile

    override suspend fun signInWithGoogle(token: String): Exception? {
        return withContext(Dispatchers.IO) {
            try {
                val firebaseCredential = GoogleAuthProvider.getCredential(token, null)
                auth.signInWithCredential(firebaseCredential).await()
                return@withContext null
            } catch (e: Exception) {
                return@withContext e
            }
        }
    }
}
