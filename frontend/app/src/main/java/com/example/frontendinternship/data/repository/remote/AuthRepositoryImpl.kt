package com.example.frontendinternship.data.repository.remote

import com.backend.proto.user.LoginAuthRequest
import com.example.frontendinternship.data.datasource.remote.datasources.AuthRemoteDataSource
import com.example.frontendinternship.domain.model.UserModel
import com.example.frontendinternship.domain.repository.AuthRepository
import jakarta.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun login(userModel: UserModel): Boolean {
        val loginAuthRequest = LoginAuthRequest.newBuilder()
            .setEmail(userModel.username)
            .setPassword(userModel.password)
            .build()
        val loginAuthResponse = authRemoteDataSource.login(loginAuthRequest)

        return !loginAuthResponse.token.isEmpty()
    }
}
