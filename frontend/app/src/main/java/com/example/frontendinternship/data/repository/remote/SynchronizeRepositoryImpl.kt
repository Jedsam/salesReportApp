package com.example.frontendinternship.data.repository.remote

import android.content.Context
import com.example.frontendinternship.data.datasource.remote.datasources.AuthRemoteDataSource
import com.example.frontendinternship.domain.model.TransactionModel
import com.example.frontendinternship.domain.repository.SynchronizeRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

class SynchronizeRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    @ApplicationContext private val context: Context
) : SynchronizeRepository {
    override suspend fun synchronize(loadTransactions: List<TransactionModel>): Boolean {
        return false
    }
}
