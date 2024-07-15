package com.f4.starwarsfactsapp.ui.screens.persons.person

import android.util.Log
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
    private val getFilmsTitlesUseCase: GetFilmsTitlesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(PersonFactsUiState())
    val uiState = _uiState.asStateFlow()

    fun getPersonFacts(personId: Int) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            val facts = personRepository.getLocalPersonFacts(personId)
            val filmTitles = getFilms(facts.films)
            _uiState.update {
                it.copy(
                    person = facts,
                    isLoading = false,
                    filmTitles = filmTitles
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
        Log.d("tag", allFilmsTitles.toString())
        val ids = getPersonFilmsIds(filmUrls)
        Log.d("tag", ids.toString())
        val filmTitles =
            allFilmsTitles.filter { title -> ids.contains(allFilmsTitles.indexOf(title)) }
        Log.d("tag", filmTitles.toString())
        return filmTitles
    }

    fun userMessageShown() {
        _uiState.update { it.copy(errorMsg = null) }
    }

    private fun getPersonFilmsIds(urls: List<String>) =
        urls.map { url -> getIdFromUrl(url) }

}
