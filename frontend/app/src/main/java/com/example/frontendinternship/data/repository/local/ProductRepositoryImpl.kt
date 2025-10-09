package com.example.frontendinternship.data.repository.local

import com.example.frontendinternship.data.datasource.local.dao.ProductDao
import com.example.frontendinternship.data.mapper.toDomain
import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.domain.repository.ProductRepository
import com.example.frontendinternship.domain.model.Product
import jakarta.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductRepository {
    override fun loadProductsByVat(vat: Int): List<Product> {
        val productEntities: List<ProductEntity> = productDao.loadAllByVat(vat)
        return productEntities.map { productEntity: ProductEntity ->
            productEntity.toDomain()
        } }
}

