package com.seallook.androidx.data.model

import com.seallook.androidx.base.DataModel
import com.seallook.androidx.base.DataModelMapper
import com.seallook.androidx.data.local.model.CounselingScheduleEntity
import com.seallook.androidx.data.remote.model.CounselingScheduleResponse
import java.util.Date

data class CounselingSchedule(
    val id: Int,
    val date: Date,
    val typeId: Int,
) : DataModel<CounselingScheduleEntity, CounselingScheduleResponse> {
    override fun toLocalModel(): CounselingScheduleEntity {
        return CounselingScheduleEntity(
            id = id,
            date = date,
            typeId = typeId,
        )
    }

    override fun toRemoteModel(): CounselingScheduleResponse {
        return CounselingScheduleResponse(
            id = id,
            date = date,
            typeId = typeId,
        )
    }

    companion object : DataModelMapper<CounselingScheduleEntity, CounselingScheduleResponse> {
        override operator fun invoke(localModel: CounselingScheduleEntity): CounselingSchedule {
            return CounselingSchedule(
                id = localModel.id,
                date = localModel.date,
                typeId = localModel.typeId,
            )
        }

        override operator fun invoke(remoteModel: CounselingScheduleResponse): CounselingSchedule {
            return CounselingSchedule(
                id = remoteModel.id,
                date = remoteModel.date,
                typeId = remoteModel.typeId,
            )
        }
    }
}
