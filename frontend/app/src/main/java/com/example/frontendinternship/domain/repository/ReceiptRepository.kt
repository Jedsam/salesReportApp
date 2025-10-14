package com.example.frontendinternship.domain.repository

import com.example.frontendinternship.domain.model.PAYMENT_METHOD
import com.example.frontendinternship.domain.model.Receipt
import com.example.frontendinternship.domain.model.VatTotalAmounts

interface ReceiptRepository {
    fun insert(vatTotalAmounts: VatTotalAmounts, paymentType: PAYMENT_METHOD)
    fun getFromDate(dateTime: String): List<Receipt>
}