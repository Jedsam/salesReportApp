package com.example.frontendinternship.data.datasource.remote.datasources

import com.backend.proto.sync.SyncRequest
import com.backend.proto.sync.SyncResponse
import com.backend.proto.sync.SyncServiceGrpcKt
import javax.inject.Inject
import io.grpc.Metadata
import io.grpc.stub.MetadataUtils

class SyncRemoteDataSource @Inject constructor(
    private val syncStub: SyncServiceGrpcKt.SyncServiceCoroutineStub,
) {
    suspend fun syncDevice(request: SyncRequest, authToken: String): SyncResponse {
        val metadata = Metadata().apply {
            put(
                Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER),
                authToken
            )
        }

        return syncStub
            .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata))
            .syncDevice(request)
    }
}