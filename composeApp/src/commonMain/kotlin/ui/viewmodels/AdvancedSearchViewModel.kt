package ui.viewmodels

import data.SearchResultData
import data.api.ApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.tatarka.inject.annotations.Inject
import moe.tlaster.precompose.stateholder.SavedStateHolder
import moe.tlaster.precompose.viewmodel.ViewModel

@Inject
class AdvancedSearchViewModel(
    private val repository: ApiRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AdvancedSearchState())
    val uiState: StateFlow<AdvancedSearchState> = _uiState.asStateFlow()

    suspend fun advancedSearch(state: AdvancedSearchState) {
        val results: List<SearchResultData> = repository.makeAdvancedSearch(state)
        _uiState.update { currentState ->
            currentState.copy(
                searchResults = results
            )
        }
    }

    fun updateSearchString(searchString: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchString = searchString
            )
        }
    }

    fun updateTermMatch(termMatch: MatchTerms) {
        _uiState.update { currentState ->
            currentState.copy(
                termMatch = termMatch
            )
        }
    }

    fun updateSearchByAnimal(searchByAnimal: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                searchByAnimal = searchByAnimal
            )
        }
    }

    fun updateAgeMin(ageMin: Int?) {
        _uiState.update { currentState ->
            currentState.copy(
                ageMin = ageMin
            )
        }
    }

    fun updateAgeMax(ageMax: Int?) {
        _uiState.update { currentState ->
            currentState.copy(
                ageMax = ageMax
            )
        }
    }

    fun updateAnimalSpecies(animalSpecies: String) {
        _uiState.update { currentState ->
            currentState.copy(
                animalSpecies = animalSpecies
            )
        }
    }

    fun updateAnimalBreed(animalBreed: String) {
        _uiState.update { currentState ->
            currentState.copy(
                animalBreed = animalBreed
            )
        }
    }

    fun updateAnimalGender(animalGender: AnimalGender) {
        _uiState.update { currentState ->
            currentState.copy(
                animalGender = animalGender
            )
        }
    }

    fun updateSearchByDrug(searchByDrug: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                searchByDrug = searchByDrug
            )
        }
    }

    fun updateActiveIngredients(activeIngredients: String) {
        _uiState.update { currentState ->
            currentState.copy(
                activeIngredients = activeIngredients
            )
        }
    }

    fun updatePreviousExposure(previousExposure: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                previousExposure = previousExposure
            )
        }
    }

    fun updateAdministrationRoute(administrationRoute: String) {
        _uiState.update { currentState ->
            currentState.copy(
                administrationRoute = administrationRoute
            )
        }
    }

    fun updateUsedAccordingToLabel(usedAccordingToLabel: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                usedAccordingToLabel = usedAccordingToLabel
            )
        }
    }

    fun updateYearStart(yearStart: Int?) {
        _uiState.update { currentState ->
            currentState.copy(
                yearStart = yearStart
            )
        }
    }

    fun updateYearEnd(yearEnd: Int?) {
        _uiState.update { currentState ->
            currentState.copy(
                yearEnd = yearEnd
            )
        }
    }

    fun updateSeriousAdverseEvent(seriousAdverseEvent: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                seriousAdverseEvent = seriousAdverseEvent
            )
        }
    }

    fun updateTreatedForAdverseEvent(treatedForAdverseEvent: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                treatedForAdverseEvent = treatedForAdverseEvent
            )
        }
    }
}

data class AdvancedSearchState(
    val searchString: String = "",
    val termMatch: MatchTerms = MatchTerms.MatchAnyTerm,

    val searchByAnimal: Boolean = false,
    val ageMin: Int? = null,
    val ageMax: Int? = null,
    val animalSpecies: String = "",
    val animalBreed: String = "",
    val animalGender: AnimalGender = AnimalGender.Male,

    val searchByDrug: Boolean = false,
    val activeIngredients: String = "",
    val previousExposure: Boolean = false,
    val administrationRoute: String = "",
    val usedAccordingToLabel: Boolean = false,

    val yearStart: Int? = null,
    val yearEnd: Int? = null,
    val seriousAdverseEvent: Boolean = true,
    val treatedForAdverseEvent: Boolean = true,

    val searchResults: List<SearchResultData> = emptyList()
)

enum class MatchTerms(val value: Boolean) {
    MatchAllTerms(true),
    MatchAnyTerm(false)
}

enum class AnimalGender(val value: Boolean) {
    Male(true),
    Female(false)
}