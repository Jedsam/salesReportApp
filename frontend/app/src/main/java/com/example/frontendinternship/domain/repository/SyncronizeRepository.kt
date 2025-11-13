package com.example.frontendinternship.domain.repository

import com.example.frontendinternship.domain.model.TransactionModel


interface SynchronizeRepository {
    suspend fun synchronize(timeString: String): Boolean
}