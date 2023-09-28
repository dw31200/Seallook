package com.seallook.androidx.data

import com.seallook.androidx.data.repository.CounselingTypeRepository
import com.seallook.androidx.data.repository.CounselingTypeRepositoryImpl
import com.seallook.androidx.data.repository.FirebaseAuthRepository
import com.seallook.androidx.data.repository.FirebaseAuthRepositoryImpl
import com.seallook.androidx.data.repository.FirebaseFirestoreRepository
import com.seallook.androidx.data.repository.FirebaseFirestoreRepositoryImpl
import com.seallook.androidx.data.repository.NaverSearchRepository
import com.seallook.androidx.data.repository.NaverSearchRepositoryImpl
import com.seallook.androidx.data.repository.SignInClientRepository
import com.seallook.androidx.data.repository.SignInClientRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {
    @Binds
    abstract fun bindNaverSearchRepository(naverSearchRepositoryImpl: NaverSearchRepositoryImpl): NaverSearchRepository

    @Binds
    abstract fun bindFirebaseAuthRepository(firebaseAuthRepositoryImpl: FirebaseAuthRepositoryImpl): FirebaseAuthRepository

    @Binds
    abstract fun bindFirebaseFirestoreRepository(firebaseFirestoreRepositoryImpl: FirebaseFirestoreRepositoryImpl): FirebaseFirestoreRepository

    @Binds
    abstract fun bindSignInClientRepository(signInClientRepositoryImpl: SignInClientRepositoryImpl): SignInClientRepository

    @Binds
    abstract fun bindCounselingTypeRepository(counselingTypeRepositoryImpl: CounselingTypeRepositoryImpl): CounselingTypeRepository
}
