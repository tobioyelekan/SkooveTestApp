package com.example.skoovetestapp.di

import com.example.skoovetestapp.data.api.SkooveApiService
import com.example.skoovetestapp.util.DefaultDispatcherProvider
import com.example.skoovetestapp.util.DispatcherProvider
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
open class NetworkModule {

    open fun getDispatchers(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient): SkooveApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .client(okHttpClient)
            .build()

        return retrofit.create(SkooveApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoroutineDispatcher(): DispatcherProvider {
        return getDispatchers()
    }

    companion object {
        private const val CONNECT_TIMEOUT = 60L
        private const val BASE_URL = "https://nomad5.com/data/skoove/"
    }

}