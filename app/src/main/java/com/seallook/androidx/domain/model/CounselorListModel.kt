package com.seallook.androidx.domain.model

import com.seallook.androidx.data.model.CounselorList

data class CounselorListModel(
    val description: String,
    val name: String,
    val thumbnail: String,
) {
    fun toDataModel(): CounselorList {
        return CounselorList(
            description = description,
            name = name,
            thumbnail = thumbnail,
        )
    }

    companion object {
        operator fun invoke(counselorList: CounselorList): CounselorListModel {
            return CounselorListModel(
                description = counselorList.description,
                name = counselorList.name,
                thumbnail = counselorList.thumbnail,
            )
        }
    }
}
