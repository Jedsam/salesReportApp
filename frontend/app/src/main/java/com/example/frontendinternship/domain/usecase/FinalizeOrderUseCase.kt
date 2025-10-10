package com.example.frontendinternship.domain.usecase

import com.example.frontendinternship.domain.model.PAYMENT_METHOD
import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.domain.model.VatTotalAmounts
import com.example.frontendinternship.domain.model.getCost
import com.example.frontendinternship.domain.repository.ReceiptRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class FinalizeOrderUseCase @Inject constructor(
    private val receiptRepository: ReceiptRepository
) {

    operator fun invoke(basketList: List<ProductWithCount>, paymentMethod: PAYMENT_METHOD){
        var totalAmounts = VatTotalAmounts()
        for (productWithCount in basketList) {
            when (productWithCount.product.vatRate) {
                0 -> totalAmounts.amountVat0 += productWithCount.getCost()
                1 -> totalAmounts.amountVat1 += productWithCount.getCost()
                10 -> totalAmounts.amountVat10 += productWithCount.getCost()
                20 -> totalAmounts.amountVat20 += productWithCount.getCost()
            }
        }
        receiptRepository.insert(totalAmounts, paymentMethod)
    }
}