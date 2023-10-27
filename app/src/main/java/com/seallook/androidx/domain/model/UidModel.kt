package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.Uid

data class UidModel(
    val id: Int,
    val uid: String,
) {
    fun toDataModel(): Uid {
        return Uid(
            id = id,
            uid = uid,
        )
    }

    companion object {
        operator fun invoke(uid: Uid): UidModel {
            return UidModel(
                id = uid.id,
                uid = uid.uid,
            )
        }
    }
}
