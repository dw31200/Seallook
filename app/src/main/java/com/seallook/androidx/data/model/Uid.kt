package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.UidEntity

data class Uid(
    val id: Int,
    val uid: String,
) {
    fun toLocalModel(): UidEntity {
        return UidEntity(
            id = id,
            uid = uid,
        )
    }

    companion object {
        operator fun invoke(uidEntity: UidEntity): Uid {
            return Uid(
                id = uidEntity.id,
                uid = uidEntity.uid,
            )
        }
    }
}
