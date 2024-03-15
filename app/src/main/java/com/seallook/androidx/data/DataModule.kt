package com.seallook.androidx.data

import com.seallook.androidx.data.repository.FirebaseStorageRepository
import com.seallook.androidx.data.repository.FirebaseStorageRepositoryImpl
import com.seallook.androidx.data.repository.auth.FirebaseAuthRepository
import com.seallook.androidx.data.repository.auth.FirebaseAuthRepositoryImpl
import com.seallook.androidx.data.repository.auth.ProfileRepository
import com.seallook.androidx.data.repository.auth.ProfileRepositoryImpl
import com.seallook.androidx.data.repository.auth.UidRepository
import com.seallook.androidx.data.repository.auth.UidRepositoryImpl
import com.seallook.androidx.data.repository.counselor.basicinfo.CounselorInfoRepository
import com.seallook.androidx.data.repository.counselor.basicinfo.CounselorInfoRepositoryImpl
import com.seallook.androidx.data.repository.counselor.counselingtype.CounselingTypeRepository
import com.seallook.androidx.data.repository.counselor.counselingtype.CounselingTypeRepositoryImpl
import com.seallook.androidx.data.repository.counselor.office.CounselorOfficeIdRepository
import com.seallook.androidx.data.repository.counselor.office.CounselorOfficeIdRepositoryImpl
import com.seallook.androidx.data.repository.counselor.office.OfficeCounselorEmailRepository
import com.seallook.androidx.data.repository.counselor.office.OfficeCounselorEmailRepositoryImpl
import com.seallook.androidx.data.repository.counselor.office.OfficeInfoRepository
import com.seallook.androidx.data.repository.counselor.office.OfficeInfoRepositoryImpl
import com.seallook.androidx.data.repository.counselor.reservation.ReservationRepository
import com.seallook.androidx.data.repository.counselor.reservation.ReservationRepositoryImpl
import com.seallook.androidx.data.repository.counselor.schedule.CounselingScheduleRepository
import com.seallook.androidx.data.repository.counselor.schedule.CounselingScheduleRepositoryImpl
import com.seallook.androidx.data.repository.kakao.KakaoSearchRepository
import com.seallook.androidx.data.repository.kakao.KakaoSearchRepositoryImpl
import com.seallook.androidx.data.repository.location.UserLocationRepository
import com.seallook.androidx.data.repository.location.UserLocationRepositoryImpl
import com.seallook.androidx.data.repository.usertype.UserTypeRepository
import com.seallook.androidx.data.repository.usertype.UserTypeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {
    @Binds
    abstract fun bindFirebaseAuthRepository(firebaseAuthRepositoryImpl: FirebaseAuthRepositoryImpl): FirebaseAuthRepository

    @Binds
    abstract fun bindProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    abstract fun bindCounselingTypeRepository(counselingTypeRepositoryImpl: CounselingTypeRepositoryImpl): CounselingTypeRepository

    @Binds
    abstract fun bindFirebaseStorageRepository(firebaseStorageRepositoryImpl: FirebaseStorageRepositoryImpl): FirebaseStorageRepository

    @Binds
    abstract fun bindCounselorInfoRepository(counselorInfoRepositoryImpl: CounselorInfoRepositoryImpl): CounselorInfoRepository

    @Binds
    abstract fun bindOfficeInfoRepository(officeInfoRepositoryImpl: OfficeInfoRepositoryImpl): OfficeInfoRepository

    @Binds
    abstract fun bindUidRepository(uidRepositoryImpl: UidRepositoryImpl): UidRepository

    @Binds
    abstract fun bindCounselingScheduleRepository(counselingScheduleRepositoryImpl: CounselingScheduleRepositoryImpl): CounselingScheduleRepository

    @Binds
    abstract fun bindReservationRepository(reservationRepositoryImpl: ReservationRepositoryImpl): ReservationRepository

    @Binds
    abstract fun bindUserTypeRepository(userTypeRepositoryImpl: UserTypeRepositoryImpl): UserTypeRepository

    @Binds
    abstract fun bindKakaoSearchRepository(kakaoSearchRepositoryImpl: KakaoSearchRepositoryImpl): KakaoSearchRepository

    @Binds
    abstract fun bindCounselorOfficeIdRepository(counselorOfficeIdRepositoryImpl: CounselorOfficeIdRepositoryImpl): CounselorOfficeIdRepository

    @Binds
    abstract fun bindOfficeCounselorEmailRepository(officeCounselorEmailRepositoryImpl: OfficeCounselorEmailRepositoryImpl): OfficeCounselorEmailRepository

    @Binds
    abstract fun bindUserLocationRepository(userLocationRepositoryImpl: UserLocationRepositoryImpl): UserLocationRepository
}
