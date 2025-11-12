package com.example.frontendinternship.utils


enum class StatusEnum(val value: String) {
    ACTIVE("active"), SUSPENDED("suspended"), CLOSED("closed");

    companion object {
        fun fromString(value: String): StatusEnum? {
            return entries.find { it.value.equals(other = value, ignoreCase = true) }
        }
    }
}