package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.repository.FirebaseFirestoreRepository
import com.seallook.androidx.data.repository.SetProfileRepository
import javax.inject.Inject

class SetProfileUseCase @Inject constructor(
    private val setProfileRepository: SetProfileRepository,
    private val firebaseFirestoreRepository: FirebaseFirestoreRepository,
) {
    suspend operator fun invoke(profile: Profile) {
        setProfileRepository.setProfile(profile)
    }

    suspend fun setProfile(user: FirebaseUser?, profile: Profile) {
        firebaseFirestoreRepository.setProfile(user, profile)
    }
}
