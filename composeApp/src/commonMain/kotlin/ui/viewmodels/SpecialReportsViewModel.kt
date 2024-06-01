package ui.viewmodels

import data.SearchResultData
import data.api.ApiRepository
import data.api.models.SearchResultApi
import data.database.DatabaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

@Inject
class SpecialReportsViewModel(
    private val repository: ApiRepository,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SpecialReportsState())
    val uiState: StateFlow<SpecialReportsState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            updateLoadingStatus(true)
            getSpecialAnimalReport(uiState.value.animal)
            updateLoadingStatus(false)
        }
    }

    suspend fun getSpecialAnimalReport(animal: Animal): List<SearchResultData> {
        val results: List<SearchResultData> = repository.makeSpecialSearch(animal.value)
        _uiState.update { currentState ->
            currentState.copy(
                searchResults = results
            )
        }
        return results
    }

    fun updateAnimal(animal: Animal) {
        _uiState.update { currentState ->
            currentState.copy(
                animal = animal
            )
        }
    }

    fun updateLoadingStatus(isLoading: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                loading = isLoading
            )
        }
    }

    fun updateEmptyResult(isEmpty: Boolean) {
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

data class SpecialReportsState(
    val animal: Animal = Animal.Dog,
    val searchResults: List<SearchResultData> = emptyList(),
    val loading: Boolean = false,
    val emptyResults: Boolean = false
)

enum class Animal(val value: String) {
    Dog("Dog"),
    Cat("Cat"),
    Goat("Goat"),
    GuineaPig("Guinea Pig"),
    Chicken("Chicken"),
    Horse("Horse"),
    Cattle("Cattle"),
    Turtle("Turtle"),
}