package com.seallook.androidx.data.device

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.seallook.androidx.data.device.service.UserLocationService
import com.seallook.androidx.data.device.service.UserLocationServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DeviceModule {
    @Binds
    abstract fun bindUserLocationApiService(userLocationServiceImpl: UserLocationServiceImpl): UserLocationService

    companion object {
        @Singleton
        @Provides
        fun provideFusedLocationProviderClient(
            @ApplicationContext context: Context,
        ): FusedLocationProviderClient {
            return LocationServices.getFusedLocationProviderClient(context)
        }
    }
}
