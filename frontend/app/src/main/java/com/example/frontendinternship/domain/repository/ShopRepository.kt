package com.example.frontendinternship.domain.repository

import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.ShopModel
import java.util.UUID


interface ShopRepository {
     suspend fun loadShops(): List<ShopModel>
    suspend fun updateShop(merchantId: UUID, shopModel: ShopModel)
}