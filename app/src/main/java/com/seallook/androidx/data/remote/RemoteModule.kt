package com.seallook.androidx.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.seallook.androidx.BuildConfig
import com.seallook.androidx.data.remote.api.firebase.storage.FirebaseStorageApiService
import com.seallook.androidx.data.remote.api.firebase.storage.FirebaseStorageApiServiceImpl
import com.seallook.androidx.data.remote.api.kakao.KakaoSearchApi
import com.seallook.androidx.data.remote.api.kakao.KakaoSearchApiService
import com.seallook.androidx.data.remote.api.kakao.KakaoSearchApiServiceImpl
import com.seallook.androidx.data.remote.auth.FirebaseAuthApiService
import com.seallook.androidx.data.remote.auth.FirebaseAuthApiServiceImpl
import com.seallook.androidx.data.remote.auth.ProfileApiService
import com.seallook.androidx.data.remote.auth.ProfileApiServiceImpl
import com.seallook.androidx.data.remote.counselor.basicinfo.CounselorInfoApiService
import com.seallook.androidx.data.remote.counselor.basicinfo.CounselorInfoApiServiceImpl
import com.seallook.androidx.data.remote.counselor.counselingtype.CounselingTypeApiService
import com.seallook.androidx.data.remote.counselor.counselingtype.CounselingTypeApiServiceImpl
import com.seallook.androidx.data.remote.counselor.office.CounselorOfficeIdApiService
import com.seallook.androidx.data.remote.counselor.office.CounselorOfficeIdApiServiceImpl
import com.seallook.androidx.data.remote.counselor.office.OfficeInfoApiService
import com.seallook.androidx.data.remote.counselor.office.OfficeInfoApiServiceImpl
import com.seallook.androidx.data.remote.counselor.reservation.ReservationApiService
import com.seallook.androidx.data.remote.counselor.reservation.ReservationApiServiceImpl
import com.seallook.androidx.data.remote.counselor.schedule.CounselingScheduleApiService
import com.seallook.androidx.data.remote.counselor.schedule.CounselingScheduleApiServiceImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteModule {
    @Binds
    abstract fun bindFirebaseAuthApiService(firebaseAuthApiServiceImpl: FirebaseAuthApiServiceImpl): FirebaseAuthApiService

    @Binds
    abstract fun bindCounselingTypeApiService(counselingTypeApiServiceImpl: CounselingTypeApiServiceImpl): CounselingTypeApiService

    @Binds
    abstract fun bindProfileApiService(profileApiServiceImpl: ProfileApiServiceImpl): ProfileApiService

    @Binds
    abstract fun bindCounselorInfoApiService(counselorInfoApiServiceImpl: CounselorInfoApiServiceImpl): CounselorInfoApiService

    @Binds
    abstract fun bindFirebaseStorageApiService(firebaseStorageApiServiceImpl: FirebaseStorageApiServiceImpl): FirebaseStorageApiService

    @Binds
    abstract fun bindOfficeInfoApiService(officeInfoApiServiceImpl: OfficeInfoApiServiceImpl): OfficeInfoApiService

    @Binds
    abstract fun bindCounselingScheduleApiService(counselingScheduleApiServiceImpl: CounselingScheduleApiServiceImpl): CounselingScheduleApiService

    @Binds
    abstract fun bindReservationApiService(reservationApiServiceImpl: ReservationApiServiceImpl): ReservationApiService

    @Binds
    abstract fun bindKakaoSearchApiService(kakaoSearchApiServiceImpl: KakaoSearchApiServiceImpl): KakaoSearchApiService

    @Binds
    abstract fun bindCounselorOfficeIdApiService(counselorOfficeIdApiServiceImpl: CounselorOfficeIdApiServiceImpl): CounselorOfficeIdApiService

    companion object {
        @Singleton
        @Provides
        fun provideAuth(): FirebaseAuth = Firebase.auth

        @Singleton
        @Provides
        fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

        @Singleton
        @Provides
        fun provideFirebaseStorage(): FirebaseStorage = Firebase.storage

        @Provides
        @WebClientId
        fun provideWebClientId(): String =
            "131258226069-ifp8m6aob9h2c9uqptc9vqgrvgm7ka1b.apps.googleusercontent.com"

        @ConnectTimeoutPolicy
        @Provides
        fun provideConnectTimeoutPolicy(): Long = 20L

        @KakaoBaseUrl
        @Provides
        fun provideKakaoBaseUrl(): String = "https://dapi.kakao.com/"

        @Singleton
        @Provides
        fun provideInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }
        }

        @Singleton
        @Provides
        fun provideOkHttpClient(
            interceptor: HttpLoggingInterceptor,
            @ConnectTimeoutPolicy connectTimeoutPolicy: Long,
        ): OkHttpClient {
            return OkHttpClient
                .Builder()
                .connectTimeout(connectTimeoutPolicy, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()
        }

        @Singleton
        @Provides
        fun provideMoshi(): Moshi {
            return Moshi
                .Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        @Singleton
        @Provides
        fun provideKakaoSearchApi(
            okHttpClient: OkHttpClient,
            moshi: Moshi,
            @KakaoBaseUrl kakaoBaseUrl: String,
        ): KakaoSearchApi {
            return Retrofit
                .Builder()
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(kakaoBaseUrl)
                .build()
                .create(KakaoSearchApi::class.java)
        }
    }
}
