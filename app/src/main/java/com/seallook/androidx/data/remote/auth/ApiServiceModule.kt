package com.seallook.androidx.data.remote.auth

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

    @Binds
    abstract fun bindBeginSignInResultApiService(beginSignInResultApiServiceImpl: BeginSignInResultApiServiceImpl): BeginSignInResultApiService

    @Binds
    abstract fun bindCurrentUserApiService(currentUserApiServiceImpl: CurrentUserApiServiceImpl): CurrentUserApiService

    @Binds
    abstract fun bindSignOutApiService(signOutApiServiceImpl: SignOutApiServiceImpl): SignOutApiService

    @Binds
    abstract fun bindSetProfileApiService(setProfileApiServiceImpl: SetProfileApiServiceImpl): SetProfileApiService

    @Binds
    abstract fun bindGetProfileApiService(getProfileApiServiceImpl: GetProfileApiServiceImpl): GetProfileApiService
}
