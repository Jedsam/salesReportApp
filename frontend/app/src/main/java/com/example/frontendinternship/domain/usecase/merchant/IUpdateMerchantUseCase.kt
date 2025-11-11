package com.example.frontendinternship.domain.usecase.merchant

import com.example.frontendinternship.domain.model.MerchantModel

interface IUpdateMerchantUseCase {
    suspend operator fun invoke(merchantModel: MerchantModel)
}
