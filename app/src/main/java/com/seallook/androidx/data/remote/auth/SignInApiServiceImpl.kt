package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.seallook.androidx.data.remote.RemoteCoroutine
import com.seallook.androidx.data.remote.model.ProfileResponse
import com.seallook.androidx.share.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import java.util.Date
import javax.inject.Inject

/* TODO
    로그인 기능 세분화
 */
class SignInApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    @RemoteCoroutine private val externalScope: CoroutineScope,
) : SignInApiService {
    private val _authStateFlow by lazy {
        callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth -> trySend(auth.currentUser) }

            auth.addAuthStateListener(listener)

            awaitClose {
                auth.removeAuthStateListener(listener)
            }
        }.shareIn(externalScope, SharingStarted.WhileSubscribed(), replay = 1)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _profile by lazy {
        _authStateFlow.flatMapLatest { user ->
            val uid = user?.uid

            if (uid == null) {
                MutableStateFlow(null)
            } else {
                db.collection(Constants.USERS).document(uid).snapshots().map {
                    if (it.exists()) {
                        Timber.d("$it")
                        return@map ProfileResponse(it)
                    } else {
                        return@map ProfileResponse(
                            "",
                            user.email ?: "",
                            user.displayName ?: "",
                            0,
                            Date(),
                            Date(),
                        )
                    }
                }
            }
        }.shareIn(externalScope, SharingStarted.WhileSubscribed(), replay = 1)
    }

    override fun getProfile() = _profile

    override suspend fun signInWithGoogle(token: String): AuthResult? {
        val firebaseCredential = GoogleAuthProvider.getCredential(token, null)
        return auth.signInWithCredential(firebaseCredential).await()
    }
}
