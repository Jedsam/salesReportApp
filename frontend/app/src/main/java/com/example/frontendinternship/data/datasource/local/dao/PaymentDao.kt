package com.example.frontendinternship.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.frontendinternship.data.model.CashPaymentEntity
import com.example.frontendinternship.data.model.CouponPaymentEntity
import com.example.frontendinternship.data.model.CreditPaymentEntity
import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.data.model.TransactionEntity
import com.example.frontendinternship.data.model.TransactionItemEntity

@Dao
interface PaymentDao {

    @Insert
    suspend fun insert(cashPaymentEntity: CashPaymentEntity)

    @Insert
    suspend fun insert(couponPaymentEntity: CouponPaymentEntity)

    @Insert
    suspend fun insert(creditPaymentEntity: CreditPaymentEntity)
}
