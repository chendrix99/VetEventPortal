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
class MainPortalViewModel(
    private val apiRepository: ApiRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainPortalState())
    val uiState: StateFlow<MainPortalState> = _uiState.asStateFlow()

    fun updateSearchString(searchString: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchString = searchString
            )
        }
    }

    suspend fun performSearch(search: String): List<SearchResultData> {
        val results: List<SearchResultData> = apiRepository.makeSimpleSearch(search)
        _uiState.update { currentState ->
            currentState.copy(
                searchResults = results
            )
        }
        return results
    }

    fun updateEmptyResults(isEmpty: Boolean) {
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

data class MainPortalState(
    val searchString: String = "",
    val searchResults: List<SearchResultData> = emptyList(),
    val emptyResults: Boolean = false
)