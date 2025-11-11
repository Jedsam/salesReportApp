package com.example.frontendinternship.domain.usecase.product

import com.example.frontendinternship.domain.model.ProductModel

interface IEditProductsUseCase {
    suspend operator fun invoke(products: List<ProductModel>)
}
