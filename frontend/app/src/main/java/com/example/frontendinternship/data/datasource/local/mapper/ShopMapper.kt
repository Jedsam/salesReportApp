package com.example.frontendinternship.data.datasource.local.mapper

import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.data.model.ShopEntity
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.ShopModel
import com.example.frontendinternship.utils.toBytes
import com.example.frontendinternship.utils.toUUID
import java.util.UUID

fun ShopEntity.toDomain(): ShopModel {
    return ShopModel(
        shopId = this.shopId.toUUID(),
        name = this.name,
        address = this.address,
        phone = this.phone,
        email = this.email,
        createdAt = this.createdAt,
        status = this.status
    )
}

fun ShopModel.toData(merchantId: UUID): ShopEntity {
    return ShopEntity(
        shopId = this.shopId.toBytes(),
        name = this.name,
        address = this.address,
        phone = this.phone,
        email = this.email,
        createdAt = this.createdAt,
        status = this.status,
        merchantId = merchantId.toBytes()
    )
}
