package com.example.frontendinternship.domain.usecase.shop

import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.ShopModel
import com.example.frontendinternship.domain.repository.ProductRepository
import com.example.frontendinternship.domain.repository.ShopRepository
import com.example.frontendinternship.domain.repository.UserRepository
import javax.inject.Inject

class UpdateShopUseCase @Inject constructor(
    private val shopRepository: ShopRepository,
    private val userRepository: UserRepository
) : IUpdateShopUseCase {


    override suspend fun invoke(shopModel: ShopModel) {
        shopRepository.updateShop(
            shopModel = shopModel,
            merchantId = userRepository.getCurrentUserID()
        )
    }
}