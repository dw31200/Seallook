package com.seallook.androidx.domain.model

import android.provider.ContactsContract.CommonDataKinds.Nickname
import com.seallook.androidx.data.model.Profile
import com.seallook.androidx.share.GenderOption
import com.seallook.androidx.share.TypeOption
import java.util.Date

data class ProfileEntity(
    val key: String,
    val email: String,
    val nickname: String,
    val name: String,
    val gender: GenderOption,
    val birth: Date,
    val type: TypeOption,
    val timestamp: Date,
) {
    fun toProfileModel(): Profile {
        return Profile(
            key = key,
            email = email,
            nickname = nickname,
            name = name,
            gender = gender,
            birth = birth,
            type = type,
            timestamp = timestamp,
        )
    }
    companion object {
        operator fun invoke(profile: Profile): ProfileEntity {
            return ProfileEntity(
                key = profile.key,
                email = profile.email,
                nickname = profile.nickname,
                name = profile.name,
                gender = profile.gender,
                birth = profile.birth,
                type = profile.type,
                timestamp = profile.timestamp,
            )
        }
    }
}