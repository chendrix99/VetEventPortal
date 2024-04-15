package ui.viewmodels

import data.SearchResultData
import data.api.ApiRepository
import data.database.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Inject
import moe.tlaster.precompose.viewmodel.ViewModel

@Inject
class SavedSearchesViewModel(
    private val repository: DatabaseRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SavedSearchesState())
    val uiState: StateFlow<SavedSearchesState> = _uiState.asStateFlow()

    suspend fun getAllSavedResults() {
        val results = repository.getAllResults()
        _uiState.update { currentState ->
            currentState.copy(
                savedSearches = results
            )
        }
    }

    suspend fun deleteAllSavedResults() {
        repository.clearAllResults()
    }
}

data class SavedSearchesState(
    val savedSearches: Flow<List<SearchResultData>> = emptyFlow()
)

