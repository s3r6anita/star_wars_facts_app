package com.f4.starwarsfactsapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.f4.starwarsfactsapp.data.model.GetFilmsResponse
import com.f4.starwarsfactsapp.data.model.GetPersonsResponse
import com.f4.starwarsfactsapp.data.network.service.StarWarsService
import com.f4.starwarsfactsapp.data.repo.FilmRepository
import com.f4.starwarsfactsapp.data.repo.FilmRepositoryImpl
import com.f4.starwarsfactsapp.data.repo.PersonRepository
import com.f4.starwarsfactsapp.data.repo.PersonRepositoryImpl
import com.f4.starwarsfactsapp.ui.screens.persons.person.GetFilmsTitlesUseCase
import com.f4.starwarsfactsapp.util.FilmResponseSerializer
import com.f4.starwarsfactsapp.util.PersonResponseSerializer
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

private val Context.personDataStore: DataStore<GetPersonsResponse> by dataStore(
    fileName = "person_storage.pb",
    serializer = PersonResponseSerializer
)
private val Context.filmDataStore: DataStore<GetFilmsResponse> by dataStore(
    fileName = "film_storage.pb",
    serializer = FilmResponseSerializer
)

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @[Provides Singleton]
    fun getPersonRepository(
        starWarsService: StarWarsService,
        @ApplicationContext appContext: Context
    ): PersonRepository {
        return PersonRepositoryImpl(starWarsService, appContext.personDataStore)
    }

    @[Provides Singleton]
    fun getFilmRepository(
        starWarsService: StarWarsService,
        @ApplicationContext appContext: Context
    ): FilmRepository {
        return FilmRepositoryImpl(starWarsService, appContext.filmDataStore)
    }

    @[Provides Singleton]
    fun getGetFilmsTitlesUseCase(
        filmRepository: FilmRepository
    ): GetFilmsTitlesUseCase {
        return GetFilmsTitlesUseCase(filmRepository)
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