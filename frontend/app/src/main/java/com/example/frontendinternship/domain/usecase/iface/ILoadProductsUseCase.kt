package com.example.frontendinternship.domain.usecase.iface

import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.UserModel

interface ILoadProductsUseCase {
    suspend operator fun invoke(): List<ProductModel>
}
