package com.example.frontendinternship.domain.usecase.product

import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.repository.ProductRepository
import java.util.UUID
import javax.inject.Inject

class RemoveProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : IRemoveProductsUseCase {
    override suspend fun invoke(uuid: UUID ) {
        return productRepository.removeProducts(uuid)
    }
}