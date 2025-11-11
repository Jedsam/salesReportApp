package com.example.frontendinternship.domain.repository

import com.example.frontendinternship.domain.model.UserModel


interface AuthRepository {
    suspend fun login(userModel: UserModel): Boolean
}