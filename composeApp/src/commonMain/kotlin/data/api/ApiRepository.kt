package data.api

import data.SearchResultData
import data.toDataModel
import di.ApplicationScope
import me.tatarka.inject.annotations.Inject

@Inject
class ApiRepository(
    private val apiService: FdaApiClient
) {

    suspend fun makeSimpleSearch(search: String): List<SearchResultData> {
        return apiService.makeSimpleApiRequest(search).map { it.toDataModel() }
    }
}