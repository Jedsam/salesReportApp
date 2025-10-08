package com.example.frontendinternship.data.repository

import com.example.frontendinternship.data.local.dao.ProductDao
import com.example.frontendinternship.data.local.dao.ReceiptDao
import com.example.frontendinternship.domain.model.PAYMENT_METHOD
import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.domain.repository.ProductRepository
import jakarta.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val receiptDao: ReceiptDao,
    private val productDao: ProductDao
) : ProductRepository {

    override fun loadProductsByVat(vat: Int) = productDao.loadAllByVat(vat)

    override fun insertReceipt(products: List<ProductWithCount>, payment: PAYMENT_METHOD) {
        receiptDao.insert(products, payment)
    }
}

