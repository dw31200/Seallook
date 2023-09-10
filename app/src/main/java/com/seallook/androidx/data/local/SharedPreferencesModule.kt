package com.seallook.androidx.data.local

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class SharedPreferencesModule {
    @Binds
    abstract fun bindSignInSharedPreferences(signInSharedPreferencesImpl: SignInSharedPreferencesImpl): SignInSharedPreferences
}