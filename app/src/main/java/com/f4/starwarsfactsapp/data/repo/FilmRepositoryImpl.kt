package com.f4.starwarsfactsapp.data.repo

import androidx.datastore.core.DataStore
import com.f4.starwarsfactsapp.data.model.GetFilmsResponse
import com.f4.starwarsfactsapp.data.model.NetworkResult
import com.f4.starwarsfactsapp.data.network.ApiHandler
import com.f4.starwarsfactsapp.data.network.service.StarWarsService
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val starWarsService: StarWarsService,
    private val dataStore: DataStore<GetFilmsResponse>
) : FilmRepository, ApiHandler {

    private suspend fun saveFilms(films: GetFilmsResponse) {
        dataStore.updateData { films }
    }

    override suspend fun getLocalFilms(): GetFilmsResponse = dataStore.data.first()

    override suspend fun getFilms(): GetFilmsResponse {
        when (val response = handleApi { starWarsService.getFilms() }) {
            is NetworkResult.Success -> {
                saveFilms(response.data)
                return getLocalFilms()
            }

            is NetworkResult.Error -> {
                return getLocalFilms().copy(error = response.errorMsg)
            }

            is NetworkResult.Exception -> {
                return getLocalFilms().copy(error = response.e.message)
            }
        }
    }
}
