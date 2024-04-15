package data.database

import data.SearchResultData
import data.toDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.tatarka.inject.annotations.Inject

@Inject
class DatabaseRepository(
    private val dataSource: ILocalDataSource
) {
    suspend fun getAllResults(): Flow<List<SearchResultData>> {
        return dataSource.getAllData().map { results ->
            withContext(Dispatchers.IO) {
                results.map { result ->
                    result.toDataModel()
                }
            }
        }
    }

    suspend fun clearAllResults() {
        dataSource.deleteData()
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
        dataSource.insertData(
            resultId = resultId,
            animalSpecies = animalSpecies,
            animalAge = animalAge,
            animalAgeUnit = animalAgeUnit,
            animalBreedComponent = animalBreedComponent,
            drugActiveIngredients = drugActiveIngredients,
            seriousAdverseEvent = seriousAdverseEvent
        )
    }
}