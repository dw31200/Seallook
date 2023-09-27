package com.seallook.androidx.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.seallook.androidx.data.repository.FirebaseFirestoreRepository
import com.seallook.androidx.data.repository.GetProfileRepository
import com.seallook.androidx.domain.model.ProfileEntity
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetProfileUseCase @Inject constructor(
    private val getProfileRepository: GetProfileRepository,
    private val firebaseFirestoreRepository: FirebaseFirestoreRepository,
) {
    suspend operator fun invoke(user: FirebaseUser?): ProfileEntity? {
        return firebaseFirestoreRepository.getProfile(user)?.let { ProfileEntity(it) }
    }
//    operator fun invoke(): Flow<ProfileEntity?> {
//        return getProfileRepository.getProfile().map {
//            it?.let { ProfileEntity(it) }
//        }
//    }
}
