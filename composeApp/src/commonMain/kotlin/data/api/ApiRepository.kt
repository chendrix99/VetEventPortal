package data.api

import data.SearchResultData
import data.toDataModel
import me.tatarka.inject.annotations.Inject
import ui.viewmodels.AdvancedSearchState

@Inject
class ApiRepository(
    private val apiService: FdaApiClient
) {
    companion object Results {
        private var currentResults: List<SearchResultData> = emptyList()
    }

    suspend fun makeSimpleSearch(search: String): List<SearchResultData> {
        currentResults = apiService.makeSimpleApiRequest(search).map { it.toDataModel() }
        return currentResults
    }

    suspend fun makeAdvancedSearch(searchState: AdvancedSearchState): List<SearchResultData> {
        currentResults = apiService.makeAdvancedApiRequest(searchState).map { it.toDataModel() }
        return currentResults
    }

    suspend fun makeSpecialSearch(animal: String): List<SearchResultData> {
        return apiService.makeSpecialApiRequest(animal).map { it.toDataModel() }
    }

    fun getResults(): List<SearchResultData> {
        return currentResults
    }

    fun clearResults() {
        currentResults = emptyList()
    }
}