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
}
