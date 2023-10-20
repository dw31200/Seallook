package com.seallook.androidx.data.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {
    @Singleton
    @Provides
    fun provideSealLookDatabase(
        @ApplicationContext context: Context,
    ): SealLookDatabase {
        return Room.databaseBuilder(
            context,
            SealLookDatabase::class.java,
            "seallook.db",
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideCounselingTypeDao(
        database: SealLookDatabase,
    ): CounselingTypeDao {
        return database.counselingTypeDao()
    }

    @Singleton
    @Provides
    fun provideOfficeInfoDao(
        database: SealLookDatabase,
    ): OfficeInfoDao {
        return database.officeInfoDao()
    }

    @Singleton
    @Provides
    fun provideCounselingScheduleDao(
        database: SealLookDatabase,
    ): CounselingScheduleDao {
        return database.counselingScheduleDao()
    }

    @Singleton
    @Provides
    fun provideReservationDao(
        database: SealLookDatabase,
    ): ReservationDao {
        return database.reservationDao()
    }

    @Singleton
    @Provides
    fun provideCounselorInfoDao(
        database: SealLookDatabase,
    ): CounselorInfoDao {
        return database.counselorInfoDao()
    }

    @Singleton
    @Provides
    fun provideProfileDao(
        database: SealLookDatabase,
    ): ProfileDao {
        return database.profileDao()
    }
}
