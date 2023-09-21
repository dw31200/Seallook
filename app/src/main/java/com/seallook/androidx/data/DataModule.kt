package com.seallook.androidx.data

import com.seallook.androidx.data.repository.BeginSignInResultRepository
import com.seallook.androidx.data.repository.BeginSignInResultRepositoryImpl
import com.seallook.androidx.data.repository.CurrentUserRepository
import com.seallook.androidx.data.repository.CurrentUserRepositoryImpl
import com.seallook.androidx.data.repository.GetProfileRepository
import com.seallook.androidx.data.repository.GetProfileRepositoryImpl
import com.seallook.androidx.data.repository.GetProfileSnapshotRepository
import com.seallook.androidx.data.repository.GetProfileSnapshotRepositoryImpl
import com.seallook.androidx.data.repository.GetTaskProfileRepository
import com.seallook.androidx.data.repository.GetTaskProfileRepositoryImpl
import com.seallook.androidx.data.repository.SetProfileRepository
import com.seallook.androidx.data.repository.SetProfileRepositoryImpl
import com.seallook.androidx.data.repository.SetUserTypeRepository
import com.seallook.androidx.data.repository.SetUserTypeRepositoryImpl
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

    @Binds
    abstract fun bindSetProfileRepository(setProfileRepositoryImpl: SetProfileRepositoryImpl): SetProfileRepository

    @Binds
    abstract fun bindGetProfileRepository(getProfileRepositoryImpl: GetProfileRepositoryImpl): GetProfileRepository

    @Binds
    abstract fun bindGetTaskProfileRepository(getTaskProfileRepositoryImpl: GetTaskProfileRepositoryImpl): GetTaskProfileRepository

    @Binds
    abstract fun bindGetProfileSnapshotRepository(getProfileSnapshotRepositoryImpl: GetProfileSnapshotRepositoryImpl): GetProfileSnapshotRepository

    @Binds
    abstract fun bindSetUserTypeRepository(setUserTypeRepositoryImpl: SetUserTypeRepositoryImpl): SetUserTypeRepository
}
