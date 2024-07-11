package com.f4.starwarsfactsapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.f4.starwarsfactsapp.data.PersonRepository
import com.f4.starwarsfactsapp.data.PersonRepositoryImpl
import com.f4.starwarsfactsapp.data.model.PersonsFactsResponse
import com.f4.starwarsfactsapp.data.network.service.StarWarsService
import com.f4.starwarsfactsapp.util.PersonsFactsResponseSerializer
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

private val Context.dataStore: DataStore<PersonsFactsResponse> by dataStore(
    fileName = "facts_storage.pb",
    serializer = PersonsFactsResponseSerializer
)

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @[Provides Singleton]
    fun getPersonRepository(
        starWarsService: StarWarsService,
        @ApplicationContext appContext: Context
    ): PersonRepository {
        return PersonRepositoryImpl(starWarsService, appContext.dataStore)
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