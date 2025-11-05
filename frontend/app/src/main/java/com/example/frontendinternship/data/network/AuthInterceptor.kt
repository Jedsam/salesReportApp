package com.example.frontendinternship.data.network

import android.util.Log
import io.grpc.CallOptions
import io.grpc.Channel
import io.grpc.ClientCall
import io.grpc.ClientInterceptor
import io.grpc.ForwardingClientCall
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : ClientInterceptor {

    companion object {
        private val AUTH_HEADER_KEY: Metadata.Key<String> =
            Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER)
    }

    override fun <ReqT, RespT> interceptCall(
        method: MethodDescriptor<ReqT, RespT>,
        callOptions: CallOptions,
        next: Channel
    ): ClientCall<ReqT, RespT> {

        if (method.fullMethodName == "com.backend.proto.user.AuthService/Login" ||
            method.fullMethodName == "com.backend.proto.user.AuthService/RegisterMerchant"
        ) {
            Log.d("AuthInterceptor", "Skipping auth for ${method.fullMethodName}")
            return next.newCall(method, callOptions)
        }

        val token = runBlocking {
            tokenManager.getToken().first()
        }

        Log.d("AuthInterceptor", "Attaching token: $token")

        return object : ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(
            next.newCall(method, callOptions)
        ) {
            override fun start(listener: Listener<RespT>?, headers: Metadata) {
                if (!token.isNullOrBlank()) {
                    headers.put(AUTH_HEADER_KEY, "Bearer $token")
                }
                super.start(listener, headers)
            }
        }
    }
}