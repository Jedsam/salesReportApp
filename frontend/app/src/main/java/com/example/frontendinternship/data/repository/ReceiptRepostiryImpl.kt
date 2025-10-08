package com.example.frontendinternship.data.repository

import com.example.frontendinternship.data.local.dao.ProductDao
import com.example.frontendinternship.data.local.dao.ReceiptDao
import jakarta.inject.Inject

class ReceiptRepostiryImpl @Inject constructor(
    private val receiptDao: ReceiptDao,
    private val productDao: ProductDao
) : ReceiptRepository {

}

