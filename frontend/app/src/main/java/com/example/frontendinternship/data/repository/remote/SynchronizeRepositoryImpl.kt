package com.example.frontendinternship.data.repository.remote

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.backend.proto.sync.CashPaymentMethod
import com.backend.proto.sync.CouponPaymentMethod
import com.backend.proto.sync.CreditPaymentMethod
import com.backend.proto.sync.Device
import com.backend.proto.sync.Firmware
import com.backend.proto.sync.Model
import com.backend.proto.sync.Product
import com.backend.proto.sync.Shop
import com.backend.proto.sync.SyncRequest
import com.backend.proto.sync.Transaction
import com.backend.proto.sync.TransactionItem
import com.example.frontendinternship.data.datasource.local.dao.DeviceDao
import com.example.frontendinternship.data.datasource.local.dao.FirmwareDao
import com.example.frontendinternship.data.datasource.local.dao.ModelDao
import com.example.frontendinternship.data.datasource.local.dao.PaymentDao
import com.example.frontendinternship.data.datasource.local.dao.ProductDao
import com.example.frontendinternship.data.datasource.local.dao.ShopDao
import com.example.frontendinternship.data.datasource.local.dao.TransactionDao
import com.example.frontendinternship.data.datasource.local.dao.TransactionItemDao
import com.example.frontendinternship.data.datasource.remote.datasources.SyncRemoteDataSource
import com.example.frontendinternship.data.datasource.remote.mapper.toProto
import com.example.frontendinternship.data.model.DeviceEntity
import com.example.frontendinternship.data.model.TransactionEntity
import com.example.frontendinternship.domain.repository.SynchronizeRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

class SynchronizeRepositoryImpl @Inject constructor(
    private val shopDao: ShopDao,
    private val deviceDao: DeviceDao,
    private val firmwareDao: FirmwareDao,
    private val modelDao: ModelDao,
    private val productDao: ProductDao,
    private val transactionDao: TransactionDao,
    private val paymentDao: PaymentDao,
    private val transactionItemDao: TransactionItemDao,
    private val syncRemoteDataSource: SyncRemoteDataSource,
    @ApplicationContext private val context: Context
) : SynchronizeRepository {
    override suspend fun synchronize(timeString: String): Boolean {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        val prefs = EncryptedSharedPreferences.create(
            context,
            "secure_prefs",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        val token = prefs.getString("jwt_token", null)

        if (token.isNullOrEmpty())
            return false

        val shop: Shop = shopDao.getCurrent().toProto()
        val deviceEntity: DeviceEntity = deviceDao.getCurrent()
        val firmware: Firmware = firmwareDao.getOne(deviceEntity.firmwareId).toProto()
        val model: Model = modelDao.getOne(deviceEntity.modelId).toProto()
        val device: Device = deviceEntity.toProto()
        val products: List<Product> = productDao.getAll().map { product -> product.toProto() }
        val transactionEntities: List<TransactionEntity> = transactionDao.getAllFromTime(timeString)
        val transactions: List<Transaction> =
            transactionEntities.map { transaction -> transaction.toProto() }
        val transactionIds: List<ByteArray> =
            transactionEntities.map { transactionEntity -> transactionEntity.transactionId }
        val cashPayments: List<CashPaymentMethod> =
            paymentDao.getCashPaymentsByTransactionIds(transactionIds)
                .map { payment -> payment.toProto() }
        val couponPayments: List<CouponPaymentMethod> =
            paymentDao.getCouponPaymentsByTransactionIds(transactionIds)
                .map { payment -> payment.toProto() }
        val creditPayments: List<CreditPaymentMethod> =
            paymentDao.getCreditPaymentsByTransactionIds(transactionIds)
                .map { payment -> payment.toProto() }
        val transactionItems: List<TransactionItem> =
            transactionItemDao.getByTransactionId(transactionIds)
                .map { transactionItem -> transactionItem.toProto() }
        val syncRequest = SyncRequest.newBuilder()
            .setShop(shop)
            .setFirmware(firmware)
            .setModel(model)
            .setDevice(device)
            .addAllProducts(products)
            .addAllTransactions(transactions)
            .addAllCashPayments(cashPayments)
            .addAllCouponPayments(couponPayments)
            .addAllCreditPayments(creditPayments)
            .addAllTransactionItems(transactionItems)
            .build()
        val syncResponse = syncRemoteDataSource.syncDevice(syncRequest, "Bearer $token")
        return syncResponse.result > 0
    }
}

