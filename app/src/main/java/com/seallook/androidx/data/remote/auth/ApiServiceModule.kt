package com.seallook.androidx.data.remote.auth

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class ApiServiceModule {
    @Binds
    abstract fun bindFirebaseAuthApiService(firebaseAuthApiServiceImpl: FirebaseAuthApiServiceImpl): FirebaseAuthApiService

    @Binds
    abstract fun bindFirebaseFirestoreApiService(firebaseFirestoreApiServiceImpl: FirebaseFirestoreApiServiceImpl): FirebaseFirestoreApiService

    @Binds
    abstract fun bindSignInClientApiService(signInClientApiServiceImpl: SignInClientApiServiceImpl): SignInClientApiService
}
