package com.example.frontendinternship.domain.usecase.product

import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.repository.ProductRepository
import java.util.UUID
import javax.inject.Inject

class EditProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : IEditProductsUseCase {

    override suspend fun invoke(products: List<ProductModel>) {
        return productRepository.editProducts(products)
    }
}