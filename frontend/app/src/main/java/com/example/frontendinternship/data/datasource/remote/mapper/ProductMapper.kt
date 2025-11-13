package com.example.frontendinternship.data.datasource.remote.mapper

import com.backend.proto.sync.Firmware
import com.backend.proto.sync.Model
import com.backend.proto.sync.Product
import com.example.frontendinternship.data.model.FirmwareEntity
import com.example.frontendinternship.data.model.ModelEntity
import com.example.frontendinternship.data.model.ProductEntity
import com.google.protobuf.ByteString

fun ProductEntity.toProto(): Product {
    return Product.newBuilder()
        .setProductId(ByteString.copyFrom(this.productId))
        .setName(this.name)
        .setPrice(this.price)
        .setVatRate(this.vatRate)
        .setIsDeleted(this.isDeleted)
        .setCreated(this.created)
        .build()
}
