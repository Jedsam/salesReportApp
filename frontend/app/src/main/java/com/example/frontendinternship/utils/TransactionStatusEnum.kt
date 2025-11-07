package com.example.frontendinternship.utils

enum class TransactionStatusEnum(val method: String) {
    ACTIVE("CASH"),
    SUSPENDED("CREDIT"),
    COUPON("COUPON");
}