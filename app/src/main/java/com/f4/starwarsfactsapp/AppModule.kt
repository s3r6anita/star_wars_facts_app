package com.f4.starwarsfactsapp

import android.content.Context
import com.f4.starwarsfactsapp.data.network.NetworkRepository
import com.f4.starwarsfactsapp.data.network.NetworkRepositoryImpl
import com.f4.starwarsfactsapp.data.network.service.StarWarsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://swapi.dev/api/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @[Provides Singleton]
    fun getNetworkRepository(
        starWarsService: StarWarsService,
    ): NetworkRepository {
        return NetworkRepositoryImpl(starWarsService)
    }

    @[Provides Singleton]
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @[Provides Singleton]
    fun provideStarWarsService(okHttpClient: OkHttpClient): StarWarsService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(StarWarsService::class.java)
    }
}