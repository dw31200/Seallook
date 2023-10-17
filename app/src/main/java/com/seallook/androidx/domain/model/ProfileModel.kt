package com.seallook.androidx.domain.model

import com.google.firebase.firestore.DocumentSnapshot
import com.seallook.androidx.data.model.Profile
import java.util.Date

data class ProfileModel(
    val key: String,
    val email: String,
    val name: String,
    val gender: Int,
    val birth: Date,
    val timestamp: Date,
) {
    // Domain -> Data
    fun toDataModel(): Profile {
        return Profile(
            key = key,
            email = email,
            name = name,
            gender = gender,
            birth = birth,
            timestamp = timestamp,
        )
    }

    companion object {
        // Mapping
        // Data -> Domain
        operator fun invoke(profile: Profile): ProfileModel {
            return ProfileModel(
                key = profile.key,
                email = profile.email,
                name = profile.name,
                gender = profile.gender,
                birth = profile.birth,
                timestamp = profile.timestamp,
            )
        }

        // DocumentSnapshot
        // Firebase Firestore에서 데이터를 가져올 때 사용하는 클래스
        // Remote -> Domain에서는 Remote 모른다
        operator fun invoke(snapshot: DocumentSnapshot): ProfileModel {
            return ProfileModel(
                key = snapshot.id,
                email = snapshot.getString("email")!!,
                name = snapshot.getString("name")!!,
                gender = snapshot.getLong("gender")!!.toInt(),
                birth = snapshot.getDate("birth")!!,
                timestamp = snapshot.getDate("timestamp")!!,
            )
        }
    }
}
