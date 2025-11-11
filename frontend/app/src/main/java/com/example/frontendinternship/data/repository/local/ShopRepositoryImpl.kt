package com.example.frontendinternship.data.repository.local

import com.example.frontendinternship.data.datasource.local.dao.ProductDao
import com.example.frontendinternship.data.datasource.local.dao.ShopDao
import com.example.frontendinternship.data.mapper.toData
import com.example.frontendinternship.data.mapper.toDomain
import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.data.model.ShopEntity
import com.example.frontendinternship.domain.repository.ProductRepository
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.ShopModel
import com.example.frontendinternship.domain.repository.ShopRepository
import com.example.frontendinternship.utils.toBytes
import jakarta.inject.Inject
import java.util.UUID

class ShopRepositoryImpl @Inject constructor(
    private val shopDao: ShopDao
) : ShopRepository {

    override suspend fun loadShops(): List<ShopModel> {
        return shopDao.getAll().map { shopEntity: ShopEntity ->
            shopEntity.toDomain()
        }
    }

    override suspend fun updateShop(merchantId: UUID, shopModel: ShopModel) {
        shopDao.editOne(shopModel.toData(merchantId))
    }
}

