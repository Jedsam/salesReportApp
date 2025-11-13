package com.example.frontendinternship.domain.repository

import com.example.frontendinternship.domain.model.ProductModel
import com.example.frontendinternship.domain.model.ShopModel
import java.util.UUID


interface UserRepository {
    suspend fun getCurrentUserID(): UUID
    suspend fun checkIfTokenNotEmpty(): Boolean
}