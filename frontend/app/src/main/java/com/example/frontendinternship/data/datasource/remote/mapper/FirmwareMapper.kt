package com.example.frontendinternship.data.datasource.remote.mapper

import com.backend.proto.sync.Firmware
import com.example.frontendinternship.data.model.FirmwareEntity
import com.google.protobuf.ByteString

fun FirmwareEntity.toProto(): Firmware {
    return Firmware.newBuilder()
        .setFirmwareId(ByteString.copyFrom(this.firmwareId))
        .setFirmwareVersion(this.firmwareVersion)
        .build()
}
