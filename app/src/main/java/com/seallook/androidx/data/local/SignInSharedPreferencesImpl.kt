package com.seallook.androidx.data.local

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.seallook.androidx.data.local.model.ProfileModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import javax.inject.Inject

class SignInSharedPreferencesImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : SignInSharedPreferences {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("profile", Context.MODE_PRIVATE)
    override fun getProfile(): ProfileModel? {
        val json = sharedPreferences.getString("profile", "")
        if (TextUtils.isEmpty(json?.trim())) return null

        return ProfileModel(JSONObject(json!!))
    }

    override fun cacheProfile(profile: ProfileModel?) {
        if (profile == null) {
            sharedPreferences.edit().remove("profile").apply()
        } else {
            sharedPreferences.edit().putString("profile", profile.toJson().toString()).apply()
        }
    }
}
