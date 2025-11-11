package com.example.frontendinternship.domain.repository

import com.example.frontendinternship.domain.model.MerchantModel
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.ShopModel
import java.util.UUID


interface MerchantRepository {
    suspend fun getCurrentMerchantID(): UUID

    suspend fun loadMerchants(): List<MerchantModel>
    suspend fun updateMerchant(userId: UUID, merchantModel: MerchantModel)
}