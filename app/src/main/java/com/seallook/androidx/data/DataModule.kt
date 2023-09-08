package com.seallook.androidx.data

import com.seallook.androidx.data.repository.SignInRepository
import com.seallook.androidx.data.repository.SignInRepositoryImpl
import com.seallook.androidx.data.repository.SignUpRepository
import com.seallook.androidx.data.repository.SignUpRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {
    @Binds
    abstract fun bindSignInRepository(signInRepositoryImpl: SignInRepositoryImpl): SignInRepository

    @Binds
    abstract fun bindSignUpRepository(signUpRepositoryImpl: SignUpRepositoryImpl): SignUpRepository
}
