package com.f4.starwarsfactsapp.data.repo

import androidx.datastore.core.DataStore
import com.f4.starwarsfactsapp.data.model.GetPlanetsResponse
import com.f4.starwarsfactsapp.data.model.NetworkResult
import com.f4.starwarsfactsapp.data.network.ApiHandler
import com.f4.starwarsfactsapp.data.network.service.StarWarsService
import com.f4.starwarsfactsapp.util.getPageFromUrl
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val starWarsService: StarWarsService,
    private val dataStore: DataStore<GetPlanetsResponse>
) : PlanetRepository, ApiHandler {

    private var nextPageId: Int? = 1

    private suspend fun savePlanets(planets: GetPlanetsResponse) {
        dataStore.updateData { planets }
    }

    override suspend fun getLocalPlanets(): GetPlanetsResponse = dataStore.data.first()

    override suspend fun getPlanets(): GetPlanetsResponse {
        while (nextPageId != -1) {
            when (val response = handleApi { starWarsService.getPlanets(nextPageId) }) {
                is NetworkResult.Success -> {
                    val oldPlanets = getLocalPlanets().results
                    val newPlanets = response.data.results
                    savePlanets(response.data.copy(results = oldPlanets + newPlanets))
                    nextPageId = getPageFromUrl(response.data.next)
                }

                is NetworkResult.Error -> {
                    return getLocalPlanets().copy(error = response.errorMsg)
                }

                is NetworkResult.Exception -> {
                    return getLocalPlanets().copy(error = response.e.message)
                }
            }
        }
        return getLocalPlanets()
    }
}