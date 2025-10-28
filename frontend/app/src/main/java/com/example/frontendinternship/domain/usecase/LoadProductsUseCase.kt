package com.example.frontendinternship.domain.usecase

import com.example.frontendinternship.domain.model.Product
import com.example.frontendinternship.domain.repository.ProductRepository
import javax.inject.Inject

class LoadProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): List<Product> {
        return productRepository.loadProducts()
    }
}
