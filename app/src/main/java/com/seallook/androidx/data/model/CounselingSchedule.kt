package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.CounselingScheduleEntity
import com.seallook.androidx.data.remote.model.CounselingScheduleResponse
import java.util.Date

data class CounselingSchedule(
    val email: String,
    val id: Int,
    val date: Date,
    val typeId: Int,
) {
    fun toLocalModel(): CounselingScheduleEntity {
        return CounselingScheduleEntity(
            email = email,
            id = id,
            date = date,
            typeId = typeId,
        )
    }

    fun toRemoteModel(): CounselingScheduleResponse {
        return CounselingScheduleResponse(
            email = email,
            id = id,
            date = date,
            typeId = typeId,
        )
    }

    companion object {
        operator fun invoke(counselingScheduleEntity: CounselingScheduleEntity): CounselingSchedule {
            return CounselingSchedule(
                email = counselingScheduleEntity.email,
                id = counselingScheduleEntity.id,
                date = counselingScheduleEntity.date,
                typeId = counselingScheduleEntity.typeId,
            )
        }

        operator fun invoke(counselingScheduleResponse: CounselingScheduleResponse): CounselingSchedule {
            return CounselingSchedule(
                email = counselingScheduleResponse.email,
                id = counselingScheduleResponse.id,
                date = counselingScheduleResponse.date,
                typeId = counselingScheduleResponse.typeId,
            )
        }
    }
}
