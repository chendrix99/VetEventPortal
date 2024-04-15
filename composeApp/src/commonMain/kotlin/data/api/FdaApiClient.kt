package data.api

import data.SearchResultData
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
import ui.viewmodels.AnimalGender
import ui.viewmodels.MatchTerms

expect fun getHttpClient(): HttpClient

@Inject
@ApplicationScope
class FdaApiClient(
    private val client: HttpClient
) : IApiClient {

    //----------------------------------------------------------------------------------------
    override suspend fun makeSimpleApiRequest(search: String): List<SearchResultApi>
    {
        return try {
            val response: HttpResponse = client.get("https://api.fda.gov/animalandveterinary/event.json?search=$search&limit=250")
            val responseBody: String = response.bodyAsText()

            if (response.status != HttpStatusCode.OK) {
                val errorResponse: ErrorResponse = decodeFromString(responseBody)
                return emptyList<SearchResultApi>()
            }

            val searchResponse: SearchResponse = decodeFromString(responseBody)
            searchResponse.results
        } catch (e: Exception) {
            val message = e.message
            emptyList<SearchResultApi>()
        }
    }

    //----------------------------------------------------------------------------------------
    override suspend fun makeAdvancedApiRequest(searchState: AdvancedSearchState): List<SearchResultApi>
    {
        val searchUrl: String = constructSearchFromState(searchState)

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

    //----------------------------------------------------------------------------------------
    suspend fun makeSpecialApiRequest(animal: String): List<SearchResultApi>
    {
        val searchUrl = "https://api.fda.gov/animalandveterinary/event.json?search=outcome.medical_status:\"Died\"+AND+animal.species:\"${animal}\"+AND+original_receive_date:[20230101+TO+20250101]&limit=10"

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

    //----------------------------------------------------------------------------------------
    private fun constructSearchFromState(state: AdvancedSearchState): String
    {
        var searchString: String = "https://api.fda.gov/animalandveterinary/event.json?search="
        val joiner: String = if (state.termMatch == MatchTerms.MatchAnyTerm) "+" else "+AND+"

        val searchTerms: List<String> = state.searchString.split(" ", ", ", ",", ": ", ":")
        for (i in 0 until searchTerms.size - 1) {
            searchString += "\"${searchTerms[i]}\"" + joiner
        }
        searchString += "\"${searchTerms[searchTerms.size - 1]}\""

        if (state.searchByAnimal) {
            if (state.animalSpecies != "") {
                searchString += "+AND+animal.species:\"${state.animalSpecies}\""
            }
            if (state.animalBreed != "") {
                searchString += "+animal.breed:\"${state.animalBreed}\""
            }
            if (state.ageMin != null) {
                searchString += "+animal.age.min:${state.ageMin.toFloat()}"
            }
            if (state.ageMax != null) {
                searchString += "+animal.age.max:${state.ageMax.toFloat()}"
            }
            searchString += when (state.animalGender) {
                AnimalGender.Male -> "+animal.gender:\"male\""
                AnimalGender.Female -> "+animal.gender:\"female\""
            }
        }

        if (state.searchByDrug) {
            if (state.activeIngredients != "") {
                searchString += "+AND+drug.active_ingredients.name:\"${state.activeIngredients}\""
            }
            if (state.administrationRoute != "") {
                searchString += "+drug.route:\"${state.administrationRoute}\""
            }
            searchString += "+drug.previous_exposure_to_drug:${state.previousExposure}"
            searchString += "+drug.used_according_to_label:${state.usedAccordingToLabel}"
        }

        searchString += "+serious_ae:${state.seriousAdverseEvent}"
        searchString += "+treated_for_ae:${state.treatedForAdverseEvent}"

        if (state.yearStart != null && state.yearEnd != null &&
            isYear(state.yearStart) && isYear(state.yearEnd)) {
            searchString += "+original_receive_date:[${state.yearStart}0101+TO+${state.yearEnd}0101]"
        }

        searchString += "&limit=250"

        return searchString
    }

    //----------------------------------------------------------------------------------------
    private fun isYear(number: Int): Boolean {
        if (number <= 0) {
            return false
        }

        val maxYear = 2100
        val minYear = 1900
        return number in minYear..maxYear
    }
}