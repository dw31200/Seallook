package com.seallook.androidx.data.repository

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.remote.auth.GetProfileSnapshotApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProfileSnapshotRepositoryImpl @Inject constructor(
    private val getProfileSnapshotApiService: GetProfileSnapshotApiService,
) : GetProfileSnapshotRepository {
    override fun getProfile(user: FirebaseUser?): Flow<Profile?> {
        return getProfileSnapshotApiService.getProfile(user).map {
            it?.let {
                Profile(it)
            }
        }
    }
}
