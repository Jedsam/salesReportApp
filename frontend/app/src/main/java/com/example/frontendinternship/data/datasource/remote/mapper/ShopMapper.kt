package com.example.frontendinternship.data.datasource.remote.mapper

import com.backend.proto.sync.Shop
import com.example.frontendinternship.data.model.ShopEntity
import com.google.protobuf.ByteString


fun ShopEntity.toProto(): Shop {
    return Shop.newBuilder()
        .setShopId(ByteString.copyFrom(this.shopId))
        .setMerchantId(ByteString.copyFrom(this.merchantId))
        .setName(this.name)
        .setAddress(this.address)
        .setPhone(this.phone)
        .setEmail(this.email)
        .setCreatedAt(this.createdAt)
        .setStatus(this.status)
        .build()
}
