package com.example.frontendinternship.data.di

import com.backend.proto.merchant.MerchantServiceGrpcKt
import com.backend.proto.user.AuthServiceGrpcKt
import com.example.frontendinternship.data.network.ApiURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.grpc.ManagedChannel
import io.grpc.okhttp.OkHttpChannelBuilder
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object GrpcModule {

    @Provides
    @Singleton
    fun provideManagedChannel(): ManagedChannel {
        return OkHttpChannelBuilder.forAddress(ApiURL.BASE_URL, ApiURL.BASE_PORT)
            .usePlaintext()
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthServiceStub(channel: ManagedChannel): AuthServiceGrpcKt.AuthServiceCoroutineStub {
        return AuthServiceGrpcKt.AuthServiceCoroutineStub(channel)
    }

    @Provides
    @Singleton
    fun provideMerchantServiceStub(channel: ManagedChannel): MerchantServiceGrpcKt.MerchantServiceCoroutineStub {
        return MerchantServiceGrpcKt.MerchantServiceCoroutineStub(channel)
    }
}