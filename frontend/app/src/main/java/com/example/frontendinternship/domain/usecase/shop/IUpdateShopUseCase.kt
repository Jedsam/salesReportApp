package com.example.frontendinternship.domain.usecase.shop

import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.ShopModel

interface IUpdateShopUseCase {
    suspend operator fun invoke(shopModel: ShopModel)
}
