package com.seallook.androidx.data.remote

import com.seallook.androidx.data.remote.auth.FirebaseAuthApiService
import com.seallook.androidx.data.remote.auth.FirebaseAuthApiServiceImpl
import com.seallook.androidx.data.remote.auth.SignInClientApiService
import com.seallook.androidx.data.remote.auth.SignInClientApiServiceImpl
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

    @Binds
    abstract fun bindFirebaseStorageApiService(firebaseStorageApiServiceImpl: FirebaseStorageApiServiceImpl): FirebaseStorageApiService
}
