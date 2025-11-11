package com.example.frontendinternship.domain.usecase.shop

import com.example.frontendinternship.domain.model.ShopModel
import com.example.frontendinternship.domain.repository.ShopRepository
import javax.inject.Inject

class LoadShopsUseCase @Inject constructor(
    private val shopRepository: ShopRepository
) : ILoadShopsUseCase {

    override suspend fun invoke(): List<ShopModel> {
        return shopRepository.loadShops()
    }
}