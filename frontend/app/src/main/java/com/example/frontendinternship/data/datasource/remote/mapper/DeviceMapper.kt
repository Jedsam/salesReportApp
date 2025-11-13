package com.example.frontendinternship.data.datasource.remote.mapper

import com.backend.proto.sync.Device
import com.backend.proto.sync.Firmware
import com.backend.proto.sync.Model
import com.example.frontendinternship.data.model.DeviceEntity
import com.example.frontendinternship.data.model.FirmwareEntity
import com.example.frontendinternship.data.model.ModelEntity
import com.google.protobuf.ByteString

fun DeviceEntity.toProto(): Device {
    return Device.newBuilder()
        .setDeviceId(ByteString.copyFrom(this.deviceId))
        .setShopId(ByteString.copyFrom(this.shopId))
        .setFirmwareId(ByteString.copyFrom(this.firmwareId))
        .setModelId(ByteString.copyFrom(this.modelId))
        .setLastSeen(this.lastSeen)
        .setCreatedAt(this.createdAt)
        .setStatus(this.status)
        .build()
}
