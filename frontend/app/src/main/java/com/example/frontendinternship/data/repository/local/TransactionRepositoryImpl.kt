package com.example.frontendinternship.data.repository.local

import com.example.frontendinternship.data.datasource.local.dao.DeviceDao
import com.example.frontendinternship.data.datasource.local.dao.PaymentDao
import com.example.frontendinternship.data.datasource.local.dao.TransactionDao
import com.example.frontendinternship.data.datasource.local.dao.TransactionItemDao
import com.example.frontendinternship.data.datasource.local.mapper.toData
import com.example.frontendinternship.data.datasource.local.mapper.toDomain
import com.example.frontendinternship.data.model.CashPaymentEntity
import com.example.frontendinternship.data.model.CouponPaymentEntity
import com.example.frontendinternship.data.model.CreditPaymentEntity
import com.example.frontendinternship.data.model.TransactionEntity
import com.example.frontendinternship.data.model.TransactionItemEntity
import com.example.frontendinternship.domain.model.ProductWithCount
import com.example.frontendinternship.domain.model.TransactionModel
import com.example.frontendinternship.domain.model.TransactionWithItemModel
import com.example.frontendinternship.domain.repository.TransactionRepository
import com.example.frontendinternship.ui.states.CashPaymentState
import com.example.frontendinternship.ui.states.CouponPaymentState
import com.example.frontendinternship.ui.states.CreditPaymentState
import com.example.frontendinternship.ui.states.PaymentState
import com.example.frontendinternship.utils.toBytes
import jakarta.inject.Inject
import java.util.UUID

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val transactionItemDao: TransactionItemDao,
    private val paymentEntityDao: PaymentDao,
    private val deviceDao: DeviceDao
) : TransactionRepository {

    override suspend fun loadTransactions(): List<TransactionModel> {
        val transactionEntities: List<TransactionEntity> = transactionDao.getAll()
        return transactionEntities.map { transactionEntity: TransactionEntity ->
            transactionEntity.toDomain()
        }
    }

    override suspend fun loadTransaction(id: String): TransactionModel? {
        return transactionDao.getById(
            UUID.fromString(id).toBytes()
        )?.toDomain()
    }

    override suspend fun getTransactionWithItemsAndProducts(id: String): TransactionWithItemModel? {
        return transactionDao.getTransactionWithItemsAndProducts(UUID.fromString(id).toBytes())
            .toDomain()
    }

    override suspend fun createCancelTransaction(
        paymentState: PaymentState,
        productBasket: List<ProductWithCount>
    ) {
        val transaction: TransactionEntity = paymentState.toData(deviceDao.getCurrent().deviceId)
        val transactionItems: List<TransactionItemEntity> = productBasket.map { product ->
            product.toData(transaction.transactionId)
        }

        transactionDao.insert(transaction)
        transactionItemDao.insertAll(transactionItems)
    }

    override suspend fun createCashTransaction(
        paymentState: PaymentState,
        cashPaymentState: CashPaymentState,
        productBasket: List<ProductWithCount>
    ) {
        val transaction: TransactionEntity = paymentState.toData(deviceDao.getCurrent().deviceId)
        val transactionItems: List<TransactionItemEntity> = productBasket.map { product ->
            product.toData(transaction.transactionId)
        }
        val cashPaymentEntity: CashPaymentEntity = cashPaymentState.toData(
            transaction.transactionId,
            transaction.total
        )

        transactionDao.insert(transaction)
        transactionItemDao.insertAll(transactionItems)
        paymentEntityDao.insert(cashPaymentEntity)
    }

    override suspend fun createCreditTransaction(
        paymentState: PaymentState,
        creditPaymentState: CreditPaymentState,
        productBasket: List<ProductWithCount>,
        authCode: String,
        cardScheme: String,
    ) {
        val transaction: TransactionEntity = paymentState.toData(deviceDao.getCurrent().deviceId)
        val transactionItems: List<TransactionItemEntity> = productBasket.map { product ->
            product.toData(transaction.transactionId)
        }
        val creditPaymentEntity: CreditPaymentEntity =
            creditPaymentState.toData(transaction.transactionId, authCode, cardScheme)

        transactionDao.insert(transaction)
        transactionItemDao.insertAll(transactionItems)
        paymentEntityDao.insert(creditPaymentEntity)
    }

    override suspend fun createCouponTransaction(
        paymentState: PaymentState,
        couponPaymentState: CouponPaymentState,
        productBasket: List<ProductWithCount>
    ) {
        val transaction: TransactionEntity = paymentState.toData(deviceDao.getCurrent().deviceId)
        val transactionItems: List<TransactionItemEntity> = productBasket.map { product ->
            product.toData(transaction.transactionId)
        }
        val couponPaymentEntity: CouponPaymentEntity =
            couponPaymentState.toData(transaction.transactionId)

        transactionDao.insert(transaction)
        transactionItemDao.insertAll(transactionItems)
        paymentEntityDao.insert(couponPaymentEntity)
    }
}