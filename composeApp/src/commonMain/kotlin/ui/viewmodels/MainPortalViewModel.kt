package ui.viewmodels

import data.SearchResultData
import data.api.ApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Inject
import moe.tlaster.precompose.viewmodel.ViewModel

@Inject
class MainPortalViewModel(
    private val repository: ApiRepository
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

    suspend fun performSearch(search: String) {
        val results: List<SearchResultData> = repository.makeSimpleSearch(search)
        _uiState.update { currentState ->
            currentState.copy(
                searchResults = results
            )
        }
    }
}

data class MainPortalState(
    val searchString: String = "",
    val searchResults: List<SearchResultData> = emptyList()
)