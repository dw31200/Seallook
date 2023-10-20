package com.seallook.androidx.data.model

import com.seallook.androidx.data.local.model.CounselingScheduleEntity
import com.seallook.androidx.data.remote.model.CounselingScheduleResponse
import java.util.Date

data class CounselingSchedule(
    val id: Int,
    val date: Date,
    val typeId: Int,
) {
    fun toLocalModel(): CounselingScheduleEntity {
        return CounselingScheduleEntity(
            id = id,
            date = date,
            typeId = typeId,
        )
    }

    fun toRemoteModel(): CounselingScheduleResponse {
        return CounselingScheduleResponse(
            id = id,
            date = date,
            typeId = typeId,
        )
    }

    companion object {
        operator fun invoke(counselingScheduleEntity: CounselingScheduleEntity): CounselingSchedule {
            return CounselingSchedule(
                id = counselingScheduleEntity.id,
                date = counselingScheduleEntity.date,
                typeId = counselingScheduleEntity.typeId,
            )
        }

        operator fun invoke(counselingScheduleResponse: CounselingScheduleResponse): CounselingSchedule {
            return CounselingSchedule(
                id = counselingScheduleResponse.id,
                date = counselingScheduleResponse.date,
                typeId = counselingScheduleResponse.typeId,
            )
        }
    }
}
