package com.example.frontendinternship.data.datasource.remote.datasources

import com.backend.proto.user.AuthServiceGrpcKt
import com.backend.proto.user.LoginAuthRequest
import com.backend.proto.user.LoginAuthResponse
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val authStub: AuthServiceGrpcKt.AuthServiceCoroutineStub
) {
    suspend fun login(request: LoginAuthRequest): LoginAuthResponse {
        return authStub.login(request)
    }
}