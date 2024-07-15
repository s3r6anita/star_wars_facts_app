package com.f4.starwarsfactsapp.ui.screens.persons.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.f4.starwarsfactsapp.data.repo.PersonRepository
import com.f4.starwarsfactsapp.util.getIdFromUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonFactsViewModel @Inject constructor(
    private val personRepository: PersonRepository,
    private val getFilmsTitlesUseCase: GetFilmsTitlesUseCase,
    private val getPlanetNamesUseCase: GetPlanetNamesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(PersonFactsUiState())
    val uiState = _uiState.asStateFlow()

    fun getPersonFacts(personId: Int) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            val facts = personRepository.getLocalPersonFacts(personId)
            val filmTitles = getFilms(facts.films)
            val homeworldName = getHomeworld(facts.homeworld)
            _uiState.update {
                it.copy(
                    person = facts,
                    filmTitles = filmTitles,
                    homeworld = homeworldName,
                    isLoading = false
                )
            }
            return@launch
        }
    }

    fun refreshPersonFact(personId: Int) {
        _uiState.update { it.copy(isRefreshing = true) }
        viewModelScope.launch(Dispatchers.IO) {
            val response = personRepository.refreshPersonFacts(personId)
            _uiState.update {
                it.copy(
                    person = response.person,
                    isRefreshing = false,
                    errorMsg = response.error
                )
            }
            return@launch
        }
    }

    private suspend fun getFilms(filmUrls: List<String>): List<String> {
        val allFilmsTitles = viewModelScope.async {
            getFilmsTitlesUseCase()
        }.await()
        val ids = getPersonFilmsIds(filmUrls)
        val filmTitles =
            allFilmsTitles.filter { title -> ids.contains(allFilmsTitles.indexOf(title)) }
        return filmTitles
    }

    private suspend fun getHomeworld(homeworldUrl: String): String {
        val allPlanetsNames = viewModelScope.async {
            getPlanetNamesUseCase()
        }.await()
        val homeworldId = getIdFromUrl(homeworldUrl)
        val homeworldName = allPlanetsNames[homeworldId]
        return homeworldName
    }

    fun userMessageShown() {
        _uiState.update { it.copy(errorMsg = null) }
    }

    private fun getPersonFilmsIds(urls: List<String>) =
        urls.map { url -> getIdFromUrl(url) }

}
