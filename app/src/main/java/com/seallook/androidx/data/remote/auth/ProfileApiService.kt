package com.seallook.androidx.data.remote.auth

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.remote.model.ProfileResponse

// TODO 기존 firestore를 사용하는 모든 메서드를 하나의 apiservice로 묶었었는데 이처럼 나누어도 될까요?
interface ProfileApiService {
    suspend fun getItem(user: FirebaseUser?): ProfileResponse?

    suspend fun setItem(user: FirebaseUser?, profile: ProfileResponse)
}
