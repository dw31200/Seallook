package com.seallook.androidx.data

import com.seallook.androidx.data.repository.BeginSignInResultRepository
import com.seallook.androidx.data.repository.BeginSignInResultRepositoryImpl
import com.seallook.androidx.data.repository.CurrentUserRepository
import com.seallook.androidx.data.repository.CurrentUserRepositoryImpl
import com.seallook.androidx.data.repository.DeleteCounselingTypeRepository
import com.seallook.androidx.data.repository.DeleteCounselingTypeRepositoryImpl
import com.seallook.androidx.data.repository.FirebaseAuthRepository
import com.seallook.androidx.data.repository.FirebaseAuthRepositoryImpl
import com.seallook.androidx.data.repository.FirebaseFirestoreRepository
import com.seallook.androidx.data.repository.FirebaseFirestoreRepositoryImpl
import com.seallook.androidx.data.repository.GetCounselingTypeRepository
import com.seallook.androidx.data.repository.GetCounselingTypeRepositoryImpl
import com.seallook.androidx.data.repository.GetNaverSearchRepository
import com.seallook.androidx.data.repository.GetNaverSearchRepositoryImpl
import com.seallook.androidx.data.repository.GetProfileRepository
import com.seallook.androidx.data.repository.GetProfileRepositoryImpl
import com.seallook.androidx.data.repository.GetProfileSnapshotRepository
import com.seallook.androidx.data.repository.GetProfileSnapshotRepositoryImpl
import com.seallook.androidx.data.repository.GetTaskProfileRepository
import com.seallook.androidx.data.repository.GetTaskProfileRepositoryImpl
import com.seallook.androidx.data.repository.GetUserTypeRepository
import com.seallook.androidx.data.repository.GetUserTypeRepositoryImpl
import com.seallook.androidx.data.repository.SetCounselingTypeRepository
import com.seallook.androidx.data.repository.SetCounselingTypeRepositoryImpl
import com.seallook.androidx.data.repository.SetProfileRepository
import com.seallook.androidx.data.repository.SetProfileRepositoryImpl
import com.seallook.androidx.data.repository.SetUserTypeRepository
import com.seallook.androidx.data.repository.SetUserTypeRepositoryImpl
import com.seallook.androidx.data.repository.SignInClientRepository
import com.seallook.androidx.data.repository.SignInClientRepositoryImpl
import com.seallook.androidx.data.repository.SignInRepository
import com.seallook.androidx.data.repository.SignInRepositoryImpl
import com.seallook.androidx.data.repository.SignOutRepository
import com.seallook.androidx.data.repository.SignOutRepositoryImpl
import com.seallook.androidx.data.repository.SignUpRepository
import com.seallook.androidx.data.repository.SignUpRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun bindSignInRepository(signInRepositoryImpl: SignInRepositoryImpl): SignInRepository

    @Singleton
    @Binds
    abstract fun bindSignUpRepository(signUpRepositoryImpl: SignUpRepositoryImpl): SignUpRepository

    @Singleton
    @Binds
    abstract fun bindBeginSignInResultRepository(beginSignInResultRepositoryImpl: BeginSignInResultRepositoryImpl): BeginSignInResultRepository

    @Singleton
    @Binds
    abstract fun bindCurrentUserRepository(currentUserRepositoryImpl: CurrentUserRepositoryImpl): CurrentUserRepository

    @Binds
    abstract fun bindSignOutRepository(signOutRepositoryImpl: SignOutRepositoryImpl): SignOutRepository

    @Binds
    abstract fun bindSetProfileRepository(setProfileRepositoryImpl: SetProfileRepositoryImpl): SetProfileRepository

    @Binds
    abstract fun bindGetProfileRepository(getProfileRepositoryImpl: GetProfileRepositoryImpl): GetProfileRepository

    @Binds
    abstract fun bindGetTaskProfileRepository(getTaskProfileRepositoryImpl: GetTaskProfileRepositoryImpl): GetTaskProfileRepository

    @Binds
    abstract fun bindGetProfileSnapshotRepository(getProfileSnapshotRepositoryImpl: GetProfileSnapshotRepositoryImpl): GetProfileSnapshotRepository

    @Binds
    abstract fun bindSetUserTypeRepository(setUserTypeRepositoryImpl: SetUserTypeRepositoryImpl): SetUserTypeRepository

    @Binds
    abstract fun bindGetUserTypeRepository(getUserTypeRepositoryImpl: GetUserTypeRepositoryImpl): GetUserTypeRepository

    @Binds
    abstract fun bindGetCounselingTypeRepository(getCounselingTypeRepositoryImpl: GetCounselingTypeRepositoryImpl): GetCounselingTypeRepository

    @Binds
    abstract fun bindSetCounselingTypeRepository(setCounselingTypeRepositoryImpl: SetCounselingTypeRepositoryImpl): SetCounselingTypeRepository

    @Binds
    abstract fun bindDeleteCounselingTypeRepository(deleteCounselingTypeRepositoryImpl: DeleteCounselingTypeRepositoryImpl): DeleteCounselingTypeRepository

    @Binds
    abstract fun bindGetNaverSearchRepository(getNaverSearchRepositoryImpl: GetNaverSearchRepositoryImpl): GetNaverSearchRepository

    @Binds
    abstract fun bindFirebaseAuthRepository(firebaseAuthRepositoryImpl: FirebaseAuthRepositoryImpl): FirebaseAuthRepository

    @Binds
    abstract fun bindFirebaseFirestoreRepository(firebaseFirestoreRepositoryImpl: FirebaseFirestoreRepositoryImpl): FirebaseFirestoreRepository

    @Binds
    abstract fun bindSignInClientRepository(signInClientRepositoryImpl: SignInClientRepositoryImpl): SignInClientRepository
}
