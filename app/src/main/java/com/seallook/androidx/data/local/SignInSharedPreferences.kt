package com.seallook.androidx.data.local

import com.seallook.androidx.data.local.model.ProfileModel

interface SignInSharedPreferences {
    fun getProfile(): ProfileModel?

    fun cacheProfile(profile: ProfileModel?)
}