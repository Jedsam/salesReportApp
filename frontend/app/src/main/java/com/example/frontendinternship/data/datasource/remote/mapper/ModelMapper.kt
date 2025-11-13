package com.example.frontendinternship.data.datasource.remote.mapper

import com.backend.proto.sync.Firmware
import com.backend.proto.sync.Model
import com.example.frontendinternship.data.model.FirmwareEntity
import com.example.frontendinternship.data.model.ModelEntity
import com.google.protobuf.ByteString

fun ModelEntity.toProto(): Model {
    return Model.newBuilder()
        .setModelId(ByteString.copyFrom(this.modelId))
        .setModelName(this.modelName)
        .build()
}
