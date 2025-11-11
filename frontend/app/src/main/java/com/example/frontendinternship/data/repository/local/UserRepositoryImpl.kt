package com.example.frontendinternship.data.repository.local

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
import jakarta.inject.Inject
import java.util.UUID

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun getCurrentUserID(): UUID {
        return userDao.getCurrent().userId.toUUID()
    }

}

