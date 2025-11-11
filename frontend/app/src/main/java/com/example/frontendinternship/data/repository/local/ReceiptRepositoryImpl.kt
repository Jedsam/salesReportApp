package com.example.frontendinternship.data.repository.local

import com.example.frontendinternship.data.datasource.local.dao.ReceiptDao
import com.example.frontendinternship.data.mapper.toDomain
import com.example.frontendinternship.data.model.ReceiptEntity
import com.example.frontendinternship.domain.model.PAYMENT_METHOD
import com.example.frontendinternship.domain.model.ReceiptModel
import com.example.frontendinternship.domain.model.VatTotalAmounts
import com.example.frontendinternship.domain.repository.ReceiptRepository
import jakarta.inject.Inject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReceiptRepositoryImpl @Inject constructor(
    private val receiptDao: ReceiptDao,
) : ReceiptRepository {

    override suspend fun insert(vatTotalAmounts: VatTotalAmounts, paymentType: PAYMENT_METHOD) {
        val currentMillis = System.currentTimeMillis()

        val date = Date(currentMillis)
        val formatter = SimpleDateFormat("yyyy-MM-dd HH", Locale.getDefault())
        val dateTime = formatter.format(date)
        receiptDao.insert(
            ReceiptEntity(
                receiptDateTime = dateTime,
                amountVat0 = vatTotalAmounts.amountVat0.toString(),
                amountVat1 = vatTotalAmounts.amountVat1.toString(),
                amountVat10 = vatTotalAmounts.amountVat10.toString(),
                amountVat20 = vatTotalAmounts.amountVat20.toString(),
                paymentType = paymentType.ordinal
            )
        )
    }

    override suspend fun getFromDate(dateTime: String): List<ReceiptModel> {
        return receiptDao.getFromDate(dateTime).map { it.toDomain() }
    }
}

