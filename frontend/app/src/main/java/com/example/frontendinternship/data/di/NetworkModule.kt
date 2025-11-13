package com.example.frontendinternship.data.di

import android.content.Context
import com.backend.proto.merchant.MerchantServiceGrpcKt
import com.backend.proto.sync.SyncServiceGrpc
import com.backend.proto.sync.SyncServiceGrpcKt
import com.backend.proto.user.AuthServiceGrpcKt
import com.example.frontendinternship.R
import com.example.frontendinternship.data.network.ApiURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.grpc.ManagedChannel
import io.grpc.okhttp.OkHttpChannelBuilder
import java.security.KeyStore
import java.security.cert.CertificateFactory
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory


@Module
@InstallIn(SingletonComponent::class)
object GrpcModule {

    @Provides
    @Singleton
    fun provideManagedChannel(@ApplicationContext context: Context): ManagedChannel {
        val cf = CertificateFactory.getInstance("X.509")

        val certInputStream = context.resources.openRawResource(R.raw.server)

        val ca = certInputStream.use {
            cf.generateCertificate(it.buffered())
        }

        val keyStoreType = KeyStore.getDefaultType()
        val keyStore = KeyStore.getInstance(keyStoreType)
        keyStore.load(null, null)
        keyStore.setCertificateEntry("ca", ca)

        val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
        val tmf = TrustManagerFactory.getInstance(tmfAlgorithm)
        tmf.init(keyStore)

        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, tmf.trustManagers, null)

        // development only domain name

        return OkHttpChannelBuilder.forAddress(ApiURL.BASE_URL, ApiURL.BASE_PORT)
            .useTransportSecurity()
            .sslSocketFactory(sslContext.socketFactory)
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

    @Provides
    @Singleton
    fun provideSyncServiceStub(channel: ManagedChannel): SyncServiceGrpcKt.SyncServiceCoroutineStub {
        return SyncServiceGrpcKt.SyncServiceCoroutineStub(channel)
    }
}