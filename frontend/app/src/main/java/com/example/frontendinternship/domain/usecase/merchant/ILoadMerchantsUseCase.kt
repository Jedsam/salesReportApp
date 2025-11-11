package com.example.frontendinternship.domain.usecase.merchant

import com.example.frontendinternship.domain.model.MerchantModel
import com.example.frontendinternship.domain.model.ShopModel

interface ILoadMerchantsUseCase {
    suspend operator fun invoke(): List<MerchantModel>
}
