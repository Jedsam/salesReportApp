package com.example.frontendinternship.domain.usecase.shop

import com.example.frontendinternship.domain.model.ShopModel

interface ILoadShopsUseCase {
    suspend operator fun invoke(): List<ShopModel>
}
