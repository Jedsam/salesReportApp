package com.example.frontendinternship.domain.usecase.merchant

import com.example.frontendinternship.domain.model.MerchantModel
import com.example.frontendinternship.domain.repository.MerchantRepository
import com.example.frontendinternship.domain.repository.UserRepository
import javax.inject.Inject

class UpdateMerchantUseCase @Inject constructor(
    private val merchantRepository: MerchantRepository,
    private val userRepository: UserRepository,
) : IUpdateMerchantUseCase {


    override suspend fun invoke(merchantModel: MerchantModel) {
        merchantRepository.updateMerchant(
            merchantModel = merchantModel,
            userId = userRepository.getCurrentUserID(),
        )
    }
}