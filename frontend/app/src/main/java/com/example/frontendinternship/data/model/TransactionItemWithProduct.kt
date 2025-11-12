package com.example.frontendinternship.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class TransactionItemWithProduct(
    @Embedded val item: TransactionItemEntity,
    @Relation(
        parentColumn = "product_id",
        entityColumn = "product_id"
    )
    val product: ProductEntity
)