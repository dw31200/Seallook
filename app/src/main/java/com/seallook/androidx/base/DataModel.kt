package com.seallook.androidx.base

interface DataModel<L : LocalModel, R : RemoteModel> {
    fun toLocalModel(): L

    fun toRemoteModel(): R
}

interface DataModelMapper<L : LocalModel, R : RemoteModel> {
    operator fun invoke(localModel: L): DataModel<L, R>

    operator fun invoke(remoteModel: R): DataModel<L, R>
}
