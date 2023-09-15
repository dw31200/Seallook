package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.seallook.androidx.data.remote.RemoteCoroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/* TODO
    로그인 기능 세분화
 */
class SignInApiServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    @RemoteCoroutine private val externalScope: CoroutineScope,
) : SignInApiService {
    override suspend fun signInWithGoogle(token: String): AuthResult? {
        val firebaseCredential = GoogleAuthProvider.getCredential(token, null)
        return auth.signInWithCredential(firebaseCredential).await()
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult? {
        return auth.signInWithEmailAndPassword(email, password).await()
    }
}
