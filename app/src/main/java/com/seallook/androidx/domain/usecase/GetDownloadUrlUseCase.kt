package com.seallook.androidx.domain.usecase

import android.net.Uri
import com.seallook.androidx.data.repository.FirebaseStorageRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetDownloadUrlUseCase @Inject constructor(
    private val firebaseStorageRepository: FirebaseStorageRepository,
) {
    suspend operator fun invoke(path: String, fileName: String): Uri? {
        return firebaseStorageRepository.getDownloadUrl(path, fileName)
    }
}