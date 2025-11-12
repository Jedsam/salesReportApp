package com.example.frontendinternship.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class TransactionWithItemsAndProducts(
    @Embedded val transaction: TransactionEntity,
    @Relation(
        parentColumn = "transaction_id",
        entityColumn = "transaction_id",
        entity = TransactionItemEntity::class
    )
    val transactionItemsWithProduct: List<TransactionItemWithProduct>
)