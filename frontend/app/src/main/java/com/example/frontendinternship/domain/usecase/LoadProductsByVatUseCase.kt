package com.example.frontendinternship.domain.usecase

import com.example.frontendinternship.domain.model.Product
import com.example.frontendinternship.domain.repository.ProductRepository
import javax.inject.Inject

class LoadProductsByVatUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(vatValue: Int): List<Product>{
        return productRepository.loadProductsByVat(vatValue)
    }
}
