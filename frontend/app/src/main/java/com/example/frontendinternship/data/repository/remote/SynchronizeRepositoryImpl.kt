package com.example.frontendinternship.data.repository.remote

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.backend.proto.sync.SyncRequest
import com.example.frontendinternship.data.datasource.remote.datasources.SyncRemoteDataSource
import com.example.frontendinternship.domain.model.TransactionModel
import com.example.frontendinternship.domain.repository.SynchronizeRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

class SynchronizeRepositoryImpl @Inject constructor(
    private val syncRemoteDataSource: SyncRemoteDataSource,
    @ApplicationContext private val context: Context
) : SynchronizeRepository {
    override suspend fun synchronize(loadTransactions: List<TransactionModel>): Boolean {
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

        val syncRequest = SyncRequest.newBuilder()
            .build()


        val syncResponse = syncRemoteDataSource.syncDevice(syncRequest, "Bearer $token")

        return syncResponse.result > 0
    }
}
