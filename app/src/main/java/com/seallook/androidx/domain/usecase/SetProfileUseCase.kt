package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.data.repository.FirebaseFirestoreRepository
import javax.inject.Inject

class SetProfileUseCase @Inject constructor(
    private val firebaseFirestoreRepository: FirebaseFirestoreRepository,
) {
    suspend operator fun invoke(user: FirebaseUser?, profile: Profile) {
        firebaseFirestoreRepository.setProfile(user, profile)
    }
}
