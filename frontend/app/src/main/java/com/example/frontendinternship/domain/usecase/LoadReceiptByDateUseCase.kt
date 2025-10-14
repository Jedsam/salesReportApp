package com.example.frontendinternship.domain.usecase

import com.example.frontendinternship.domain.model.Receipt
import com.example.frontendinternship.domain.repository.ReceiptRepository
import javax.inject.Inject

class LoadReceiptByDateUseCase
@Inject constructor(
    private val receiptRepository: ReceiptRepository
) {
    operator fun invoke(xmlReportString: String): List<Receipt> {
        return receiptRepository.getFromDate(xmlReportString)
    }
}