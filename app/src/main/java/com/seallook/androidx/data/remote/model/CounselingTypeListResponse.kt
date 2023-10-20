package com.seallook.androidx.data.remote.model

import com.google.firebase.firestore.DocumentSnapshot
import com.google.gson.Gson
import com.squareup.moshi.Moshi

data class CounselingTypeListResponse(
    val counselingList: List<CounselingTypeResponse>,
) {
    constructor() : this(emptyList())

    fun toJson(): String {
        return Moshi.Builder().build().adapter(CounselingTypeListResponse::class.java).toJson(this)
    }

    companion object {
        operator fun invoke(snapshot: DocumentSnapshot): List<CounselingTypeResponse> {
            val list = mutableListOf<CounselingTypeResponse>()
            val result = Gson().fromJson(snapshot.getString("counselingList"), List::class.java)
            for (element in result) {
                list.add(Gson().fromJson(element.toString(), CounselingTypeResponse::class.java))
            }
            return list
        }
    }
}
