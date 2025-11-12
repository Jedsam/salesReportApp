package com.example.frontendinternship.data.repository.remote

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.backend.proto.user.LoginAuthRequest
import com.example.frontendinternship.data.datasource.remote.datasources.AuthRemoteDataSource
import com.example.frontendinternship.domain.model.UserModel
import com.example.frontendinternship.domain.repository.AuthRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    @ApplicationContext private val context: Context
) : AuthRepository {
    override suspend fun login(userModel: UserModel): Boolean {
        val loginAuthRequest = LoginAuthRequest.newBuilder()
            .setEmail(userModel.username)
            .setPassword(userModel.password)
            .build()
        val loginAuthResponse = authRemoteDataSource.login(loginAuthRequest)

        val token = loginAuthResponse.token
        if (token.isNotEmpty()) {
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

            prefs.edit().putString("jwt_token", token).apply()
            return true
        }
        return false
    }
}
