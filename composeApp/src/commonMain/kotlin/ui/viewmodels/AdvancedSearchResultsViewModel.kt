package ui.viewmodels

import data.SearchResultData
import data.api.ApiRepository
import data.database.DatabaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Inject
import moe.tlaster.precompose.viewmodel.ViewModel

@Inject
class AdvancedSearchResultsViewModel(
    private val repository: ApiRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AdvancedSearchResultsState())
    val uiState: StateFlow<AdvancedSearchResultsState> = _uiState.asStateFlow()

    fun updateResults() {
        _uiState.update { currentState ->
            currentState.copy(
                searchResults = repository.getResults()
            )
        }
        updateEmptyResults(repository.getResults().isEmpty())
    }

    fun clearResults() {
        repository.clearResults()
        _uiState.update { currentState ->
            currentState.copy(
                searchResults = emptyList()
            )
        }
    }

    private fun updateEmptyResults(isEmpty: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                emptyResults = isEmpty
            )
        }
    }

    suspend fun saveResult(
        resultId: String?,
        animalSpecies: String?,
        animalAge: Double?,
        animalAgeUnit: String?,
        animalBreedComponent: String?,
        drugActiveIngredients: String?,
        seriousAdverseEvent: String?
    ) {
        databaseRepository.saveResult(
            resultId,
            animalSpecies,
            animalAge,
            animalAgeUnit,
            animalBreedComponent,
            drugActiveIngredients,
            seriousAdverseEvent
        )
    }
}

data class AdvancedSearchResultsState(
    val searchResults: List<SearchResultData> = emptyList(),
    val emptyResults: Boolean = false
)