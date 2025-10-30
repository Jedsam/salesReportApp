package com.example.frontendinternship.domain.usecase.iface

import com.example.frontendinternship.domain.model.Product

interface ILoadProductsUseCase {
    suspend operator fun invoke(): List<Product>
}
