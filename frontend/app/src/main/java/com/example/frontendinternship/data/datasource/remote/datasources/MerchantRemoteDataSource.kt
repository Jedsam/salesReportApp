package com.example.frontendinternship.data.datasource.remote.datasources

import com.backend.proto.merchant.MerchantGetRequest
import com.backend.proto.merchant.MerchantGetResponse
import com.backend.proto.merchant.MerchantServiceGrpcKt
import javax.inject.Inject

class MerchantRemoteDataSource @Inject constructor(
    private val merchantStub: MerchantServiceGrpcKt.MerchantServiceCoroutineStub
) {

    suspend fun getMerchantInfo(request: MerchantGetRequest): MerchantGetResponse {
        return merchantStub.getMerchantInfo(request)
    }
}