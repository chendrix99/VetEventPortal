package data.api

import data.api.models.SearchResultApi
import ui.viewmodels.AdvancedSearchState

interface IApiClient {
    suspend fun makeSimpleApiRequest(search: String): List<SearchResultApi>

    suspend fun makeAdvancedApiRequest(searchState: AdvancedSearchState): List<SearchResultApi>
}