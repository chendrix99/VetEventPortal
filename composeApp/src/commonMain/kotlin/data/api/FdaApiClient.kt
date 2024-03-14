package data.api

import data.api.models.ErrorResponse
import data.api.models.SearchResponse
import data.api.models.SearchResultApi
import di.ApplicationScope
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json.Default.decodeFromString
import me.tatarka.inject.annotations.Inject
import ui.viewmodels.AdvancedSearchState

expect fun getHttpClient(): HttpClient

@Inject
@ApplicationScope
class FdaApiClient(
    private val client: HttpClient
) : IApiClient {
    override suspend fun makeSimpleApiRequest(search: String): List<SearchResultApi> {
        return try {
            val response: HttpResponse = client.get("https://api.fda.gov/animalandveterinary/event.json?search=$search&limit=50")
            val responseBody: String = response.bodyAsText()

            if (response.status != HttpStatusCode.OK) {
                val errorResponse: ErrorResponse = decodeFromString(responseBody)
                return emptyList<SearchResultApi>()
            }

            val searchResponse: SearchResponse = decodeFromString(responseBody)
            searchResponse.results
        } catch (e: Exception) {
            emptyList<SearchResultApi>()
        }
    }

    override suspend fun makeAdvancedApiRequest(searchState: AdvancedSearchState): List<SearchResultApi>
    {
        val searchUrl: String = ConstructSearchFromState(searchState)

        return try {
            val response: HttpResponse = client.get(searchUrl)
            val responseBody: String = response.bodyAsText()

            if (response.status != HttpStatusCode.OK) {
                val errorResponse: ErrorResponse = decodeFromString(responseBody)
                return emptyList<SearchResultApi>()
            }

            val searchResponse: SearchResponse = decodeFromString(responseBody)
            searchResponse.results
        } catch (e: Exception) {
            emptyList<SearchResultApi>()
        }
    }

    private fun ConstructSearchFromState(state: AdvancedSearchState): String
    {
        // TODO

        return "https://api.fda.gov/animalandveterinary/event.json"
    }
}