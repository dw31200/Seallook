package com.seallook.androidx.domain.usecase.counselorinfo.basic

import android.net.Uri
import com.seallook.androidx.data.repository.FirebaseStorageRepository
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetDownloadUrlUseCase @Inject constructor(
    private val firebaseStorageRepository: FirebaseStorageRepository,
) {
    suspend operator fun invoke(path: String, fileName: String): Result<Uri> {
        return runCatching {
            firebaseStorageRepository.getDownloadUrl(path, fileName)
        }
    }
}
