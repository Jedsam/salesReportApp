package com.example.frontendinternship.domain.usecase.implementation

import com.example.frontendinternship.domain.model.Product
import com.example.frontendinternship.domain.repository.ProductRepository
import com.example.frontendinternship.domain.usecase.iface.ILoadProductsUseCase
import javax.inject.Inject

class LoadProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : ILoadProductsUseCase {
    override suspend fun invoke(): List<Product> {
        return productRepository.loadProducts()
    }
}