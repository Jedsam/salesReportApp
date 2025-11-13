package com.example.frontendinternship.data.repository.local

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.frontendinternship.data.datasource.local.dao.ProductDao
import com.example.frontendinternship.data.datasource.local.dao.ShopDao
import com.example.frontendinternship.data.datasource.local.dao.UserDao
import com.example.frontendinternship.data.mapper.toData
import com.example.frontendinternship.data.mapper.toDomain
import com.example.frontendinternship.data.model.ProductEntity
import com.example.frontendinternship.data.model.ShopEntity
import com.example.frontendinternship.domain.repository.ProductRepository
import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.ShopModel
import com.example.frontendinternship.domain.repository.ShopRepository
import com.example.frontendinternship.domain.repository.UserRepository
import com.example.frontendinternship.utils.toBytes
import com.example.frontendinternship.utils.toUUID
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import java.util.UUID

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    @ApplicationContext private val context: Context,
) : UserRepository {
    override suspend fun getCurrentUserID(): UUID {
        return userDao.getCurrent().userId.toUUID()
    }

    override suspend fun checkIfTokenNotEmpty(): Boolean {
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
        return !token.isNullOrEmpty()
    }

}

