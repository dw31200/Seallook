package com.seallook.androidx.data.remote

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class ApiServiceModule {
    @Binds
    abstract fun bindSignInApiService(signInApiServiceImpl: SignInApiServiceImpl): SignInApiService

    @Binds
    abstract fun bindSignUpApiService(signUpApiServiceImpl: SignUpApiServiceImpl): SignUpApiService
}