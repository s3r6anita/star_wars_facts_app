package com.f4.starwarsfactsapp.ui.screens.persons.person

import com.f4.starwarsfactsapp.data.repo.FilmRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetFilmsTitlesUseCase(
    private val filmRepository: FilmRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): List<String> =
        withContext(defaultDispatcher) {
            val response = filmRepository.getLocalFilms()
            response.results.map { it.title }
        }
}
