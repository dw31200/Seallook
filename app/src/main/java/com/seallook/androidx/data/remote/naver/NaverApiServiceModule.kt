package com.seallook.androidx.data.remote.naver

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class NaverApiServiceModule {
    @Binds
    abstract fun bindNaverSearchApiService(naverSearchApiServiceImpl: NaverSearchApiServiceImpl): NaverSearchApiService
}
