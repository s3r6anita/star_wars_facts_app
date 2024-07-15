package com.f4.starwarsfactsapp.ui.screens.persons.person

import com.f4.starwarsfactsapp.data.repo.PlanetRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPlanetNamesUseCase(
    private val planetRepository: PlanetRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): List<String> =
        withContext(defaultDispatcher) {
            val response = planetRepository.getLocalPlanets()
            response.results.map { it.name }
        }
}
