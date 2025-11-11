package com.example.frontendinternship.data.repository.local

import com.example.frontendinternship.data.datasource.local.dao.ProductDao
import com.example.frontendinternship.data.mapper.toData
import com.example.frontendinternship.data.mapper.toDomain
import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.domain.repository.ProductRepository
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.utils.toBytes
import jakarta.inject.Inject
import java.util.UUID

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductRepository {

    override fun loadProducts(): List<ProductModel> {
        val productEntities: List<ProductEntity> = productDao.getAllNotDeleted()
        return productEntities.map { productEntity: ProductEntity ->
            productEntity.toDomain()
        }
    }

    override fun removeProducts(uuid: UUID) {
        productDao.markDeleted(uuid.toBytes())
    }

    override fun editProducts(products: List<ProductModel>) {
        productDao.editAll(products.map { productModel -> productModel.toData(isDeleted = false) })
    }

    override fun addProducts(products: List<ProductModel>) {
        productDao.addAll(products.map { productModel -> productModel.toData(isDeleted = false) })
    }
}

