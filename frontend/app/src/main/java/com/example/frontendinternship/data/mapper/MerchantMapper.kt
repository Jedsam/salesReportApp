package com.example.frontendinternship.data.mapper

import com.example.frontendinternship.data.model.MerchantEntity
import com.example.frontendinternship.domain.model.MerchantModel
import com.example.frontendinternship.utils.toBytes
import com.example.frontendinternship.utils.toUUID
import java.util.UUID

fun MerchantEntity.toDomain(): MerchantModel {
    return MerchantModel(
        merchantId = this.merchantId.toUUID(),
        name = this.name,
        businessName = this.businessName,
        phone = this.phone,
        address = this.address,
        createdAt = this.createdAt,
        status = this.status
    )
}

fun MerchantModel.toData(userId: UUID): MerchantEntity {
    return MerchantEntity(
        merchantId = this.merchantId?.toBytes() ?: UUID.randomUUID().toBytes(),
        userId = userId.toBytes(),
        name = this.name,
        businessName = this.businessName,
        phone = this.phone,
        address = this.address,
        createdAt = this.createdAt,
        status = this.status,
    )
}
