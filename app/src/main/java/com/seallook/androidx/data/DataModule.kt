package com.seallook.androidx.data

import com.seallook.androidx.data.repository.BeginSignInResultRepository
import com.seallook.androidx.data.repository.BeginSignInResultRepositoryImpl
import com.seallook.androidx.data.repository.CurrentUserRepository
import com.seallook.androidx.data.repository.CurrentUserRepositoryImpl
import com.seallook.androidx.data.repository.SignInRepository
import com.seallook.androidx.data.repository.SignInRepositoryImpl
import com.seallook.androidx.data.repository.SignOutRepository
import com.seallook.androidx.data.repository.SignOutRepositoryImpl
import com.seallook.androidx.data.repository.SignUpRepository
import com.seallook.androidx.data.repository.SignUpRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun bindSignInRepository(signInRepositoryImpl: SignInRepositoryImpl): SignInRepository

    @Singleton
    @Binds
    abstract fun bindSignUpRepository(signUpRepositoryImpl: SignUpRepositoryImpl): SignUpRepository

    @Singleton
    @Binds
    abstract fun bindBeginSignInResultRepository(beginSignInResultRepositoryImpl: BeginSignInResultRepositoryImpl): BeginSignInResultRepository

    @Singleton
    @Binds
    abstract fun bindCurrentUserRepository(currentUserRepositoryImpl: CurrentUserRepositoryImpl): CurrentUserRepository

    @Binds
    abstract fun bindSignOutRepository(signOutRepositoryImpl: SignOutRepositoryImpl): SignOutRepository
}
