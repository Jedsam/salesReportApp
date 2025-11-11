package com.example.frontendinternship.domain.usecase.merchant

import com.example.frontendinternship.domain.model.MerchantModel
import com.example.frontendinternship.domain.repository.MerchantRepository
import javax.inject.Inject

class LoadMerchantsUseCase @Inject constructor(
    private val merchantRepository: MerchantRepository
) : ILoadMerchantsUseCase {

    override suspend fun invoke(): List<MerchantModel> {
        return merchantRepository.loadMerchants()
    }
}