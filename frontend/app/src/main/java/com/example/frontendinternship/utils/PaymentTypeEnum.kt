package com.example.frontendinternship.utils

enum class PaymentTypeEnum(val value: String) {
    CASH("cash"),
    CREDIT("credit"),
    COUPON("coupon");

    companion object {
        fun fromString(value: String): PaymentTypeEnum? {
            return PaymentTypeEnum.entries.find {
                it.value.equals(
                    other = value,
                    ignoreCase = true
                )
            }
        }
    }
}